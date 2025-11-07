
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
                data: [
                    { name: 'Masculino', y: 598 },
                    { name: 'Feminino', y: 625 },
                    { name: 'Outro', y: 25 }
                ]
            }]
        });

        // 2. Gráfico de Colunas: Especialidades Mais Demandadas
        Highcharts.chart('especialidadesChart', {
            chart: { type: 'column' },
            title: { text: null },
            xAxis: { categories: ['Clínica Geral', 'Pediatria', 'Ginecologia', 'Cardiologia', 'Dermatologia'] },
            yAxis: { title: { text: 'Número de Consultas' } },
            series: [{
                name: 'Consultas',
                 data:[120, 95, 80, 65, 50],
                color: '#4caf50'
            }]
        });

        // 3. Gráfico de Área Empilhada: Marcações por Tipo
        Highcharts.chart('tipoMarcacoesChart', {
            chart: { type: 'area' },
            title: { text: null },
            xAxis: { categories: ['Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out'] },
            yAxis: { title: { text: 'Quantidade' } },
            plotOptions: { area: { stacking: 'normal' } },
           series: [
    {
      name: 'Consulta',
      data: [100, 110, 120, 130, 140, 150]
    },
    {
      name: 'Exame',
      data: [60, 70, 80, 90, 100, 110]
    },
    {
      name: 'Urgência',
      data: [20, 25, 30, 35, 40, 45]
    }
  ]
        });

        // 4. Gráfico de Linhas: Taxa de Cancelamento Mensal (%)
        Highcharts.chart('cancelamentoChart', {
            chart: { type: 'line' },
            title: { text: null },
            xAxis: { categories: ['Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out'] },
            yAxis: { 
                title: { text: 'Taxa de Cancelamento (%)' },
                max: 100
            },
            series: [{
                name: 'Cancelamento',
                 data: [10.2, 11.5, 12.0, 11.8, 12.3, 12.5],
                color: '#f44336'
            }],
            tooltip: { valueSuffix: ' %' }
        });
    