# DELI-cious
I built a deli application that would work on any store or restraunt. The deli application will manage user's order and reciepts. When the user run's this application they will have list of option that will allow them to interact with the application to make they're order. After the order been made the reciept will be saved in the csv file and printed out to the console.

## Base Structure
![baseStructure](images/baseStructure2.png)

* Product class will have two childern classes the will inherit from it. The class are sandwish, drink, and chips. The order class will be responseble for taking the order and also containing the array list of products. It will also keep track of the order number, date, and customer's name.

![structureOfTopping](images/toppingBaseStructure.png)

* Topping class will have two childern classes. One class named regularTopping and another named premiumToppings. The regular topping will be included with the order, so the customer won't get charged for regular toppings. However the premium topping class will handle if the topping is meat, cheese, extra meat, or extra cheese and charge the user extra based on the sandwish size.