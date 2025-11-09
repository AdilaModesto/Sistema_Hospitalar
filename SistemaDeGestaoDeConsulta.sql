-- 1. Resumo geral das tabelas
SELECT 'Administrador' AS tabela, COUNT(*) AS total FROM Administrador
UNION ALL
SELECT 'Telefone_Admin', COUNT(*) FROM Telefone_Admin
UNION ALL
SELECT 'Paciente', COUNT(*) FROM Paciente
UNION ALL
SELECT 'Paciente_Telefone', COUNT(*) FROM Paciente_Telefone
UNION ALL
SELECT 'Medico', COUNT(*) FROM Medico
UNION ALL
SELECT 'Medico_Especialidade', COUNT(*) FROM Medico_Especialidade
UNION ALL
SELECT 'Medico_Disponibilidade', COUNT(*) FROM Medico_Disponibilidade
UNION ALL
SELECT 'Marcacao', COUNT(*) FROM Marcacao
UNION ALL
SELECT 'Consulta', COUNT(*) FROM Consulta
UNION ALL
SELECT 'Exame', COUNT(*) FROM Exame
UNION ALL
SELECT 'Resultado', COUNT(*) FROM Resultado
UNION ALL
SELECT 'Receita', COUNT(*) FROM Receita
UNION ALL
SELECT 'Medicamento', COUNT(*) FROM Medicamento
UNION ALL
SELECT 'Urgencia', COUNT(*) FROM Urgencia;

-- 2. Consultas por estado
SELECT estado, COUNT(*) AS total
FROM Marcacao
GROUP BY estado
ORDER BY estado;

-- 3. Consultas por tipo
SELECT tipo_consulta, COUNT(*) AS total
FROM Consulta
GROUP BY tipo_consulta
ORDER BY total DESC;

-- 4. Exames por tipo
SELECT tipo_exame, COUNT(*) AS total
FROM Exame
GROUP BY tipo_exame
ORDER BY total DESC;

-- 5. Resultados por doença
SELECT doenca, COUNT(*) AS total
FROM Resultado
GROUP BY doenca
ORDER BY total DESC;

-- 6. Receitas e quantidade de medicamentos
SELECT r.id_receita, COUNT(m.medicamento) AS qtd_medicamentos
FROM Receita r
LEFT JOIN Medicamento m ON m.id_receita = r.id_receita
GROUP BY r.id_receita
ORDER BY qtd_medicamentos DESC
LIMIT 10;  -- top 10 receitas com mais medicamentos

-- 7. Pacientes com mais consultas
SELECT p.id_paciente, p.nome, p.apelido, COUNT(c.id_consulta) AS total_consultas
FROM Paciente p
LEFT JOIN Consulta c ON c.id_marcacao IN (SELECT id_marcacao FROM Marcacao WHERE id_paciente = p.id_paciente)
GROUP BY p.id_paciente
ORDER BY total_consultas DESC
LIMIT 10;

-- 8. Urgências por prioridade
SELECT prioridade, COUNT(*) AS total
FROM Urgencia
GROUP BY prioridade
ORDER BY total DESC;







