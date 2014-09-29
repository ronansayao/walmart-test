walmart-test
============

WalmartTest

Introdução
==========
Este é um projeto baseado em servicços Rest em Java onde é possível inserir mapas de determinadas localidades.
Baseado nos mapas é possível encontrar a menos rota e calcular custos de viagens.

O sistema recebe dois tipos de dados de interface via Webservices os Mapas (com.walmart.logistics.pathfinder.vo.MapVO) 
que contém as informações do mapa na url http://<server_name>:<port>/rest/map/create para a criação.

Após a criação o sistema disponibiliza o serviço  http://<server_name>:<port>/rest/route (passando com.walmart.logistics.pathfinder.vo.PathEntriesVO) 
que obtém o menor caminho baseado na origem e destino e calcula os custos baseado na autonomia e no custo do combustível.

Para poder fazer a busca de uma melhor forma foi implementado dois algorítmos de busca em Grafos.
Dijkstra e o AStar
O algorítmo escolhido pode ser alterado via config.properties.
Em alguns casos o dijkstra se mostrou mais eficiente então optamos pode deixá-lo como padrão.

Arquitetura
===========

Tenologias utilizadas:

- Java 1.7
- Oracle Database Server Express Edition 
- Hibernate
- JPA
- Spring(context, web, webmvc, orm, data-jpa, test, junit)
- Jackson
- Log4j
- GraphStream
- Jetty 8.14

Testes Unitário
===============
Foram criados alguns testes unitário com o intuito de quebrar caso o código tenha sido alterado.
Também foi criado um teste para avaliação de performance do algorítmo.
