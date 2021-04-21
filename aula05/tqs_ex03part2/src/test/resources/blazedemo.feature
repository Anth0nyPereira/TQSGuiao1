#language: pt
Funcionalidade: Comprar viagem de avião

  Cenário: Verificar viagens disponíveis
    Quando eu navego para a página "https://blazedemo.com/"
    E eu escolho como cidade de origem "Paris"
    E eu escolho como cidade de destino "London"
    E eu clico em Find Flights
    Então eu sou redirecionado para a página "https://blazedemo.com/reserve.php"
    E no cabeçalho da página aparecem tanto "Paris" como "London"