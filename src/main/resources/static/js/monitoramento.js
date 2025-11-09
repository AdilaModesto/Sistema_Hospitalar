
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
            const categoriasCancelamento = data.dadosCancelamento ? data.dadosCancelamento.map(c => c.mes) : [];
            const taxaCancelamento = data.dadosCancelamento ? data.dadosCancelamento.map(c => c.taxa) : [];

            Highcharts.chart('cancelamentoChart', {
                chart: { type: 'line' },
                title: { text: null },
                xAxis: { categories: categoriasCancelamento },
                yAxis: {
                    title: { text: 'Taxa de Cancelamento (%)' },
                    max: 100
                },
                series: [{
                    name: 'Cancelamento',
                    data: taxaCancelamento,
                    color: '#f44336'
                }],
                tooltip: { valueSuffix: ' %' }
            });
        }).catch(err => console.error('Erro ao carregar dados do dashboard:', err));
});