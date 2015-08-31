-- Traz valor bruto das contas por usu�rio

SELECT sum(saldo_inicial) FROM financa.conta where usuario_id=1;


SELECT sum(valor) as valor FROM financa.despesa JOIN 
financa.conta
ON conta_id = conta.id where usuario_id = ? 


SELECT sum(valor) as valor FROM financa.compras_cartao JOIN
financa.cartao_credito ON cartao_credito_id = cartao_credito.id WHERE usuario_id = 1


SELECT valor, cartao_credito.descricao, cartao_credito_id, usuario_id FROM financa.compras_cartao JOIN financa.cartao_credito 
ON cartao_credito.id = compras_cartao.cartao_credito_id  where usuario_id = 2;