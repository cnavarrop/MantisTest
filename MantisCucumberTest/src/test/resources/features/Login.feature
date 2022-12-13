Feature: Ingreso a la APP Mantis

  Background: 
    Given la url de acceso a Mantis

  Scenario: Login correcto en mantis
    When ingreso un usuario "usuario" valido
    And ingreso una contraseña "contraseña" valida
    Then se ingresa y se muestra la pantalla home de mantis

  Scenario: No ingresar un usuario y presionar submit
    When no realizo el ingreso un usuario "usuario"
    Then presiono la opcion submit
    And me muestra un mensaje de error
