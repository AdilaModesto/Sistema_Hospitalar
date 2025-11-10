
// Quando o formulário é submetido, deixamos o servidor tratar a autenticação via /perform-login
document.getElementById('loginForm').addEventListener('submit', function (e) {
    const perfil = document.getElementById('perfil').value;
    if (!perfil) {
        e.preventDefault();
        alert('Selecione um perfil antes de continuar!');
    }
});
