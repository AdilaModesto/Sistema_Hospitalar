
document.getElementById('loginForm').addEventListener('submit', function () {
    const perfil = document.getElementById('perfil').value;
    if (perfil === "PACIENTE") {
        this.action = "/dashboardPaciente";
    } else if (perfil === "MEDICO") {
        this.action = "/dashboardMedico";
    } else if (perfil === "ADMIN") {
        this.action = "/dashboardAdmin";
    } else {
        e.preventDefault(); // impede envio se nenhum perfil foi escolhido
        alert("Selecione um perfil antes de continuar!");
    }
});
