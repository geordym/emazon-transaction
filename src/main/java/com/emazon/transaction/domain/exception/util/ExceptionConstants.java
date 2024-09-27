package com.emazon.transaction.domain.exception.util;

public class ExceptionConstants {


    public static final String ARTICLE_NOT_FOUND_ERROR = "Article not found";
    public static final String ARTICLE_NOT_FOUND_ERROR_MESSAGE = "The article id you provide does not exist";


    public static final String SUPPLY_NOT_FOUND_ERROR= "Supply not found";
    public static final String SUPPLY_NOT_FOUND_ERROR_MESSAGE= "You need to provide a valid Supply ID";

    public static final String ARRIVING_SUPPLY_NOT_FOUND_ERROR = "Supply In way Not Found";
    public static final String ARRIVING_SUPPLY_NOT_FOUND_MESSAGE = "Dont have in way supplies for this article";


    public static final String SHOPPINGCART_EMPTY_ERROR = "Shopping cart empty";
    public static final String SHOPPINGCART_EMPTY_MESSAGE = "Shopping Cart does not have articles, add one to buy";


    public static final String ARTICLE_WITHOUT_STOCK_ERROR = "We does not have stock of this article";
    public static final String ARTICLE_WITHOUT_STOCK_MESSAGE = "We cannot decrease stock of an article because we dont have stock of them";

}
