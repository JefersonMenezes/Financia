-- Traz valor bruto das contas por usuário

SELECT sum(saldo_inicial) FROM financa.conta where usuario_id=1;


SELECT sum(valor) as valor FROM financa.despesa JOIN 
financa.conta
ON conta_id = conta.id where usuario_id = ?";  


SELECT sum(valor) as valor FROM financa.compras_cartao JOIN
financa.cartao_credito 

ON cartao_credito_id = cartao_credito.id WHERE usuario_id = 1; 