@ProductsAutomation @UI
  Feature: Products Automation Exercise
    @AddProductToCart
    Scenario: A user should be able to add a product to the cart
      Given I navigate to Automation product page
      When The user adds the product to the cart
      Then The message should be displayed

    @ViewProduct
    Scenario: A user should be able to go to the product details
      Given I navigate to Automation product page
      When I go to the product details of the selected product
      Then I should be redirected to the product details

    @DeployCategory
    Scenario Outline: A user should be able to deploy the category options
      Given I navigate to Automation product page
      When I deploy the category options of the "<category>" selected
      Then The options of the "<category>" selected should deploy correctly
      Examples:
        | category  |
        | Women     |
        | Men       |
        | Kids      |


    @FilterByBrands
    Scenario Outline: A user should be able to filter products by brands
      Given I navigate to Automation product page
      When I should be redirected to the product page
      Then I should be redirected to the products of the "<brand>" selected
      And The products of the selected "<brand>" should be displayed
      Examples:
        | brand  |
        | Polo   |
        | H&M    |
        | Madame |
        | Mast & Harbour |
        | Kookie Kids    |
        | Allen Solly Junior |
        | Babyhug            |
        | Biba               |

    @ModifyProductQuantity
    Scenario Outline: A user should be able to modify the product's quantity
      Given I navigate to Automation product page
      When I go to the product details of the selected product
      Then I modify the "<quantity>" of products
      Then I add the product to the cart
      And The message of add selected product should be displayed
      Examples:
        |quantity|
        |   1    |
        |   10   |
        |   100  |
        |  -10   |

    @AddProductDetails
    Scenario: A user should be able to access to the product page
      Given I navigate to Automation product page
      When I go to the product details of the selected product
      Then I add the product to the cart
      And The message of add product should be displayed

    @SendReview
    Scenario: A user should be able to send a review of the product
      Given I navigate to Automation product page
      When I go to the product details of the selected product
      Then I send the review of the product
        | name    | email                      | review            |
        | Rodrigo | rodrigo.valda@jalasoft.com | this is my review |
      And The successful alert should be displayed