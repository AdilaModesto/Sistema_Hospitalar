
document.addEventListener('DOMContentLoaded', function () {
    // Buscar dados do dashboard
    fetch('/api/dashboard-data')
        .then(response => response.json())
        .then(data => {
            console.log('Dashboard data:', data); // debug
            // Atualizar métricas (usar querySelectorAll para evitar seletores nth-child incorretos)
            const metrics = document.querySelectorAll('.metric');
            if (metrics && metrics.length >= 3) {
                metrics[0].textContent = data.totalPacientes != null ? data.totalPacientes : '—';
                metrics[1].textContent = data.marcacoesHoje != null ? data.marcacoesHoje : '—';
                // formatar taxa com 1 casa decimal
                metrics[2].textContent = data.taxaCancelamento != null ? Number(data.taxaCancelamento).toFixed(1) + '%' : '—';
            } else {
                // fallback: tentar selecionar individualmente por posição relativa
                console.warn('Elemento .metric não encontrado em quantidade esperada', metrics);
            }
            // 1. Gráfico de Pizza: Sexo dos Pacientes
            Highcharts.chart('sexoChart', {
                chart: { type: 'pie' },
                title: { text: null },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: { enabled: true, format: '{point.name}: {point.percentage:.1f}%' }
                    }
                },
                series: [{
                    name: 'Pacientes',
                    colorByPoint: true,
                    data: data.dadosGenero ? data.dadosGenero.map(g => ({ name: g.genero, y: g.quantidade })) : []
                }]
            });

            // 2. Gráfico de Colunas: Especialidades Mais Demandadas
            Highcharts.chart('especialidadesChart', {
                chart: { type: 'column' },
                title: { text: null },
                xAxis: { categories: data.dadosEspecialidade ? data.dadosEspecialidade.map(e => e.especialidade) : [] },
                yAxis: { title: { text: 'Número de Consultas' } },
                series: [{
                    name: 'Consultas',
                    data: data.dadosEspecialidade ? data.dadosEspecialidade.map(e => e.quantidade) : [],
                    color: '#4caf50'
                }]
            });

            // 3. Gráfico de Área Empilhada: Marcações por Tipo (últimos 6 meses)
            const categoriasMarcacoes = data.dadosMarcacoes ? data.dadosMarcacoes.map(m => m.mes) : [];
            const consultasSeries = data.dadosMarcacoes ? data.dadosMarcacoes.map(m => m.consultas) : [];
            const examesSeries = data.dadosMarcacoes ? data.dadosMarcacoes.map(m => m.exames) : [];
            const urgenciasSeries = data.dadosMarcacoes ? data.dadosMarcacoes.map(m => m.urgencias) : [];

            Highcharts.chart('tipoMarcacoesChart', {
                chart: { type: 'area' },
                title: { text: null },
                xAxis: { categories: categoriasMarcacoes },
                yAxis: { title: { text: 'Quantidade' } },
                plotOptions: { area: { stacking: 'normal' } },
                series: [
                    { name: 'Consulta', data: consultasSeries },
                    { name: 'Exame', data: examesSeries },
                    { name: 'Urgência', data: urgenciasSeries }
                ]
            });

            // 4. Gráfico de Linhas: Taxa de Cancelamento Mensal (%)
            const categoriasRealizadas = data.dadosRealizadas ? data.dadosRealizadas.map(c => c.mes) : [];
            const taxaRealizadas = data.dadosRealizadas ? data.dadosRealizadas.map(c => c.taxa) : [];

            Highcharts.chart('cancelamentoChart', {
                chart: { type: 'line' },
                title: { text: null },
                xAxis: { categories: categoriasRealizadas },
                yAxis: {
                    title: { text: 'Taxa de Marcações Realizadas (%)' },
                    max: 100
                },
                series: [{
                    name: 'Realizadas',
                    data: taxaRealizadas,
                    color: '#f44336'
                }],
                tooltip: { valueSuffix: ' %' }
            });

            // 5. Preencher tabela de Últimas Urgências
            const ultimasUrgenciasBody = document.getElementById('ultimasUrgenciasBody');
            if (ultimasUrgenciasBody) {
                if (data.ultimasUrgencias && data.ultimasUrgencias.length > 0) {
                    ultimasUrgenciasBody.innerHTML = data.ultimasUrgencias.map(u => {
                        const corPrioridade = u.prioridade === 'Alta' ? '#d32f2f' :
                            u.prioridade === 'Média' ? '#f57c00' : '#4caf50';
                        return `<tr>
                            <td>${u.paciente}</td>
                            <td style="color: ${corPrioridade}; font-weight: bold;">${u.prioridade}</td>
                            <td>${u.data}</td>
                        </tr>`;
                    }).join('');
                } else {
                    ultimasUrgenciasBody.innerHTML = '<tr><td colspan="3" style="text-align: center; color: #999;">Sem dados disponíveis</td></tr>';
                }
            }

            // 6. Preencher tabela de Médicos Mais Ativos (top 5, decrescente)
            const medicosMaisAtivosBody = document.getElementById('medicosMaisAtivosBody');
            if (medicosMaisAtivosBody) {
                // aceitar variações no JSON: tentar encontrar array com médicos
                let medicoArray = [];
                if (Array.isArray(data.medicosAtivos) && data.medicosAtivos.length > 0) {
                    medicoArray = data.medicosAtivos.slice();
                } else if (Array.isArray(data.medicos_ativos) && data.medicos_ativos.length > 0) {
                    medicoArray = data.medicos_ativos.slice();
                } else if (Array.isArray(data.medicos) && data.medicos.length > 0) {
                    medicoArray = data.medicos.slice();
                }

                // Normalizar formatos: objetos com chaves diferentes ou arrays de arrays
                medicoArray = medicoArray.map(item => {
                    if (!item) return { nome: '—', consultas: 0 };
                    // caso seja array [nome, quantidade]
                    if (Array.isArray(item)) {
                        return { nome: item[0] || '—', consultas: item[1] != null ? Number(item[1]) : 0 };
                    }
                    // caso seja objeto com chaves variadas
                    if (typeof item === 'object') {
                        const nome = item.nome || item.medico || item.nomeMedico || item.nome_medico || item[0] || '—';
                        const consultas = item.consultas || item.quantidade || item.qtd || item.count || item[1] || 0;
                        return { nome: nome, consultas: consultas != null ? Number(consultas) : 0 };
                    }
                    // fallback
                    return { nome: String(item), consultas: 0 };
                });

                // garantir ordenação decrescente por consultas
                medicoArray.sort((a, b) => Number(b.consultas) - Number(a.consultas));
                // pegar top 5
                const top5 = medicoArray.slice(0, 5);
                // completar com placeholders se menos que 5
                while (top5.length < 5) {
                    top5.push({ nome: '—', consultas: 0 });
                }

                // gerar HTML
                medicosMaisAtivosBody.innerHTML = top5.map((m, idx) => `
                    <tr>
                        <td>${m.nome}</td>
                        <td>${m.consultas}</td>
                    </tr>
                `).join('');
            }
        }).catch(err => console.error('Erro ao carregar dados do dashboard:', err));
});