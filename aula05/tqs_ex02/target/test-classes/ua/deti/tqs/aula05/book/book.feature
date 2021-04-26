Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.

  Scenario: Search books by publication year
    Given a book with the title 'One good book', written by 'Anonymous', published in 2013-03-14
    And another book with the title 'Some other book', written by 'Tim Tomson', published in 2014-08-23
    And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published in 2012-01-01
    When the customer searches for books published between 2013 and 2014
    Then 2 books should have been found
    And Book 1 should have the title 'Some other book'
    And Book 2 should have the title 'One good book'

  Scenario: Search book by author
    Given a book with the title 'Outlander', written by 'Diana Gabaldon', published in 1991-06-01
    And another book with the title 'Dragonfly in Amber', written by 'Diana Gabaldon', published in 1992-07-01
    And another book with the title 'The Catcher in the Rye', written by 'J. D. Salinger', published in 1951-07-16
    When a customer looks up for books by the author 'Diana Gabaldon'
    Then 2 books should have been found
    And Book 1 should have the title 'Outlander'
    And Book 2 should have the title 'Dragonfly in Amber'


  Scenario: Search book with a word in common
    Given a book with the title 'Demon Slayer', written by 'Koyoharu Gotouge', published in 2016-02-15
    And another book with the title 'Goblin Slayer', written by 'Kagyu Kumo', published in 2016-05-25
    And another book with the title 'BTOOOM!', written by 'Junya Inoue', published in 2009-06-19
    And another book with the title 'Slayers', written by 'Hajime Kanzaka', published in 1990-01-17
    When a customer searches for books that have 'Slayer' in the title
    Then 3 books should have been found
    And Book 1 should have the title 'Demon Slayer'
    And Book 2 should have the title 'Goblin Slayer'
    And Book 3 should have the title 'Slayers'
