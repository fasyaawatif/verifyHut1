<?php
 
class DbOperation
{
    //Database connection link
    private $con;
 
    //Class constructor
    function __construct()
    {
        //Getting the DbConnect.php file
        require_once dirname(__FILE__) . '/DbConnect.php';
 
        //Creating a DbConnect object to connect to the database
        $db = new DbConnect();
 
        //Initializing our connection link of this class
        //by calling the method connect of DbConnect class
        $this->con = $db->connect();
    }
	
	/*
	* The create operation
	* When this method is called a new record is created in the database
	*/
	function createProduct($productID, $productName, $productType, $productBrand){
		$stmt = $this->con->prepare("INSERT INTO product_db (productID, productName, productType, productBrand) VALUES (?, ?, ?, ?)");
		$stmt->bind_param("ssss", $productID, $productName, $productType, $productBrand);
		if($stmt->execute())
			return true; 
		return false; 
	}
	
	/*
	* The read operation
	* When this method is called it is returning all the existing record of the database
	*/
	function getProducts(){
		$stmt = $this->con->prepare("SELECT productID, productName, productType, productBrand FROM product_db");
		$stmt->execute();
		$stmt->bind_result($productID, $productName, $productType, $productBrand);
		
		$products = array(); 
		
		while($stmt->fetch()){
			$product  = array();
			$product['productID'] = $productID; 
			$product['productName'] = $productName; 
			$product['productType'] = $productType; 
			$product['productBrand'] = $productBrand;
			 
			
			array_push($products, $product); 
		}
		
		return $products; 
	}
	
	/*
	* The update operation
	* When this method is called the record with the given id is updated with the new given values
	*/
	function updateProduct($productID, $productName, $productType, $productBrand){
		$stmt = $this->con->prepare("UPDATE product_db SET  productName = ?, productType = ?, productBrand = ? WHERE productID = ?");
		$stmt->bind_param("ssss", $productName, $productType, $productBrand, $productID);
		if($stmt->execute())
			return true; 
		return false; 
	}
	
	/*
	* The delete operation
	* When this method is called record is deleted for the given id 
	*/
	function deleteProduct($productID){
		$stmt = $this->con->prepare("DELETE FROM product_db WHERE productID = ? ");
		$stmt->bind_param("i", $productID);
		if($stmt->execute())
			return true; 
		
		return false; 
	}
}	
