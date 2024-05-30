# DELI-cious
I built a deli application that would work on any store or restraunt. The deli application will manage user's order and reciepts. When the user run's this application they will have list of option that will allow them to interact with the application to make they're order. After the order been made the reciept will be saved in the csv file and printed out to the console.

## Base Structure
![baseStructure](images/baseStructure2.png)

* Product class will have two childern classes the will inherit from it. The class are sandwish, drink, and chips. The order class will be responseble for taking the order and also containing the array list of products. It will also keep track of the order number, date, and customer's name.

![structureOfTopping](images/toppingBaseStructure.png)

* Topping class will have two childern classes. One class named regularTopping and another named premiumToppings. The regular topping will be included with the order, so the customer won't get charged for regular toppings. However the premium topping class will handle if the topping is meat, cheese, extra meat, or extra cheese and charge the user extra based on the sandwish size.

## New Order List menu
![new order list menu](images/newOrderListMenu.png)

### add sandwich
* Once the user selects add sandwich ("1"). The user will get prompted for the type of bread, meat, cheese, regular topping, sauces, and weither if the sandwich is toasted or not. Once the user enters the meat and cheese toppings, it will charge the user based on weither these topping are extra or not. It will make a object topping for regular and premium based on the topping and then it will get added to sandwich arrayList of toppings.

Getting sandwich

```java
public void addSandWish(Order order) {

        // getting type of bread and sandwich size
        String typeOfBread = ui.getTypeOfBread();
        int sandWishSize = ui.getSandWishSize();

        // getting type of meat from ui
        String[] meat = ui.getSandWishMeat();
        Sandwich sandwish = new Sandwich(meat[0] + "Sandwich", sandWishSize, typeOfBread);
        addMeat(sandwish, meat);

        // getting sandwich Cheese from ui
        String[] cheese =  ui.getSandWishCheese();
        addCheese(sandwish, cheese);

        //getting regular topping from ui
        String[] regularToppings = ui.getRegularTopping();
        addRegularTopping(sandwish,regularToppings);

        //getting sauces for ui
        String[] sauces = ui.getSauces();
        addToppings(sandwish, sauces);

        // checking if the sandwich is toasted from ui
        sandwish.setToasted(ui.isSandwichToasted());

        ArrayList<Toppings> toppings = sandwish.getToppings();

        for (Toppings topping : toppings)
        {
            System.out.printf(" %s - %.2f \n", topping.getType(), topping.getPrice());
        }
    }
```

* Once the user enter's ("2"). The user will prompted for drink size and type. The program will then make a product object type of drink. Then it will be added to the order's products ArrayList.

* Once the user enter's ("3"). To add a chips to they're order. They will be prompted for the chips type then it will be added to the orders product arrayList. 

* Once the user enter's ("4") to checkout. The receipt will be printed out and the user will have the option to cancel the order or confirm it. If The user confrims the receipt will get saved to receipts directory.

![checkout](images/checkOut.png)

## Signature Sandwiches

