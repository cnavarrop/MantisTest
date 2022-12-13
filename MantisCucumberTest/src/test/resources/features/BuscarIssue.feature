Feature: Buscar una incidencia

@TetsPostLogin
Scenario: Buscar incidencia por ID
    Given Estando logeado en el sistema Mantis
    When voy a la opcion del menu ver incidencias
    Then Me posiciono sobre el buscador de incidencias
    And escribo el numero Id de la incidencia
    And realizo la busqueda
    And Se muestra en la grilla la incidencia buscada
    
    




