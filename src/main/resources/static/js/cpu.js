// Dados simulados
let cpuData = [];
let loadAvgData = [];

// Atualizar timestamp
function updateTimestamp() {
  const now = new Date();
  document.getElementById("timestamp").textContent = now.toLocaleString('pt-BR');
}

// Simular dados
function simulateData() {
  const now = new Date().getTime();
  const cpu = Math.floor(Math.random() * 100);
  const temp = 40 + Math.floor(Math.random() * 30);
  const load = (Math.random() * 4).toFixed(2);

  // Atualizar KPIs
  document.getElementById("cpu-usage").textContent = cpu + "%";
  document.getElementById("cpu-temp").textContent = temp + "°C";
  document.getElementById("cpu-load").textContent = load;

  // Gráfico de CPU
  cpuData.push([now, cpu]);
  if (cpuData.length > 20) cpuData.shift();
  window.cpuChart?.series[0].setData(cpuData);

  // Gráfico de núcleos
  const cores = [
    { name: 'Núcleo 1', y: Math.floor(Math.random() * cpu) },
    { name: 'Núcleo 2', y: Math.floor(Math.random() * cpu) },
    { name: 'Núcleo 3', y: Math.min(Math.floor(Math.random() * cpu), 100 - cpu) },
    { name: 'Núcleo 4', y: Math.min(Math.floor(Math.random() * cpu), 100 - cpu) }
  ];
  window.coresChart?.series[0].setData(cores);

  // Gráfico de carga média
  loadAvgData.push([now, parseFloat(load)]);
  if (loadAvgData.length > 20) loadAvgData.shift();
  window.loadChart?.series[0].setData(loadAvgData);

  // Atualizar tabela de processos
  const processos = [
    { name: 'System', pid: 0, cpu: Math.floor(Math.random() * 15) },
    { name: 'Chrome', pid: 1234, cpu: Math.floor(Math.random() * 25) },
    { name: 'Node.js', pid: 5678, cpu: Math.floor(Math.random() * 30) },
    { name: 'Firefox', pid: 9101, cpu: Math.floor(Math.random() * 20) }
  ];

  const tbody = document.getElementById('processosBody');
  tbody.innerHTML = '';
  processos.forEach(p => {
    const tr = document.createElement('tr');
    tr.innerHTML = `<td>${p.name}</td><td>${p.pid}</td><td>${p.cpu}%</td>`;
    tbody.appendChild(tr);
  });

  // Atualizar timestamp
  updateTimestamp();
}

// Inicializar gráficos
document.addEventListener("DOMContentLoaded", function () {
  updateTimestamp();

  // Gráfico: Uso de CPU
  window.cpuChart = Highcharts.chart('cpuChart', {
    title: { text: null },
    chart: { type: 'line' },
    xAxis: { type: 'datetime', title: { text: 'Tempo' } },
    yAxis: { title: { text: 'Uso (%)' }, min: 0, max: 100 },
    series: [{ name: 'CPU (%)', data: cpuData }]
  });

  // Gráfico: Uso por núcleo
  window.coresChart = Highcharts.chart('coresChart', {
    title: { text: null },
    chart: { type: 'pie' },
    plotOptions: { pie: { dataLabels: { enabled: true, format: '{point.name}: {point.y:.1f}%' } } },
    series: [{ name: 'Uso (%)', data: [] }]
  });

  // Gráfico: Carga média
  window.loadChart = Highcharts.chart('loadChart', {
    title: { text: null },
    chart: { type: 'area' },
    xAxis: { type: 'datetime', title: { text: 'Tempo' } },
    yAxis: { title: { text: 'Carga' } },
    series: [{ name: 'Carga Média', data: loadAvgData }]
  });

  // Atualizar a cada 2 segundos
  setInterval(simulateData, 2000);
});