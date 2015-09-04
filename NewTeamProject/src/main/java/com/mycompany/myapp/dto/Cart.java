package dao;

public class Cart {
   private String memberId;
   private int cartNo; 
   private int productNo; 
   private String productName;
   private int cartAmount;
   private String cartTotalPrice;
   private int productPrice;
   public int getProductPrice() {
      return productPrice;
   }
   public void setProductPrice(int productPrice) {
      this.productPrice = productPrice;
   }
   public int getCartNo() {
      return cartNo;
   }
   public void setCartNo(int cartNo) {
      this.cartNo = cartNo;
   }
   public int getProductNo() {
      return productNo;
   }
   public void setProductNo(int productNo) {
      this.productNo = productNo;
   }
   public String getProductName() {
      return productName;
   }
   public void setProductName(String productName) {
      this.productName = productName;
   }
   public int getCartAmount() {
      return cartAmount;
   }
   public void setCartAmount(int cartAmount) {
      this.cartAmount = cartAmount;
   }
   public String getCartTotalPrice() {
      return cartTotalPrice;
   }
   public void setCartTotalPrice(String cartTotalPrice) {
      this.cartTotalPrice = cartTotalPrice;
   }


   
   
}