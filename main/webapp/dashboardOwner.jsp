<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dinetime.dinetime.Classes.Owner" %>
<%@ page import="com.dinetime.dinetime.Classes.Restaurant" %>
<%
    Owner owner = (Owner) session.getAttribute("owner");
    Restaurant[] ownerAllRestaurants;
    if (owner!=null){
        ownerAllRestaurants = owner.getAllRestaurants();
    }else {
        ownerAllRestaurants = new Restaurant[0];
        System.out.println("NO Restaurants found");
    }

%>
<!DOCTYPE html>
<html lang="en">


<!-- molla/index-1.html  22 Nov 2019 09:55:06 GMT -->
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Molla - Bootstrap eCommerce Template</title>
    <meta name="keywords" content="HTML5 Template">
    <meta name="description" content="Molla - Bootstrap eCommerce Template">
    <meta name="author" content="p-themes">
    <!-- Favicon -->
    <link rel="apple-touch-icon" sizes="180x180" href="assets/images/icons/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="assets/images/icons/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="assets/images/icons/favicon-16x16.png">
    <link rel="manifest" href="assets/images/icons/site.html">
    <link rel="mask-icon" href="assets/images/icons/safari-pinned-tab.svg" color="#666666">
    <link rel="shortcut icon" href="assets/images/icons/favicon.ico">
    <meta name="apple-mobile-web-app-title" content="Molla">
    <meta name="application-name" content="Molla">
    <meta name="msapplication-TileColor" content="#cc9966">
    <meta name="msapplication-config" content="assets/images/icons/browserconfig.xml">
    <meta name="theme-color" content="#ffffff">
    <link rel="stylesheet" href="assets/vendor/line-awesome/line-awesome/line-awesome/css/line-awesome.min.css">
    <!-- Plugins CSS File -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/plugins/owl-carousel/owl.carousel.css">
    <link rel="stylesheet" href="assets/css/plugins/magnific-popup/magnific-popup.css">
    <link rel="stylesheet" href="assets/css/plugins/jquery.countdown.css">
    <!-- Main CSS File -->
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="stylesheet" href="assets/css/skins/skin-demo-2.css">
    <link rel="stylesheet" href="assets/css/demos/demo-2.css">
</head>

<body>
<div class="page-wrapper">
    <header class="header header-2 header-intro-clearance">
        <div class="header-top">
            <div class="container">
                <div class="header-left">
                    <p>Special restaurants are already available.</p><a href="#">&nbsp;Read more ...</a>
                </div><!-- End .header-left -->
                <div class="header-right">
                    <ul class="top-menu">
                        <li>
                            <a href="#">Links</a>
                            <ul>
                                <li>
                                    <div class="header-dropdown">
                                        <a href="#">USD</a>
                                        <div class="header-menu">
                                            <ul>
                                                <li><a href="#">Eur</a></li>
                                                <li><a href="#">Usd</a></li>
                                            </ul>
                                        </div><!-- End .header-menu -->
                                    </div>
                                </li>
                                <li>
                                    <div class="header-dropdown">
                                        <a href="#">English</a>
                                        <div class="header-menu">
                                            <ul>
                                                <li><a href="#">English</a></li>
                                                <li><a href="#">French</a></li>
                                                <li><a href="#">Spanish</a></li>
                                            </ul>
                                        </div><!-- End .header-menu -->
                                    </div>
                                </li>
                                <li><a href="#signin-modal" data-toggle="modal">Sign in / Sign up</a></li>
                            </ul>
                        </li>
                    </ul><!-- End .top-menu -->
                </div><!-- End .header-right -->

            </div><!-- End .container -->
        </div><!-- End .header-top -->

        <div class="header-middle">
            <div class="container">
                <div class="header-left">
                    <button class="mobile-menu-toggler">
                        <span class="sr-only">Toggle mobile menu</span>
                        <i class="icon-bars"></i>
                    </button>

                    <a href="index.jsp" class="logo">
                        <img src="assets/images/demos/demo-2/logo.png" alt="Molla Logo" width="105" height="25">
                    </a>
                </div><!-- End .header-left -->

                <div class="header-center">
                    <div class="header-search header-search-extended header-search-visible header-search-no-radius d-none d-lg-block">
                        <a href="#" class="search-toggle" role="button"><i class="icon-search"></i></a>
                        <form action="#" method="get">
                            <div class="header-search-wrapper search-wrapper-wide">
                                <label for="q" class="sr-only">Search</label>
                                <input type="search" class="form-control" name="q" id="q" placeholder="Search product ..." required>
                                <button class="btn btn-primary" type="submit"><i class="icon-search"></i></button>
                            </div><!-- End .header-search-wrapper -->
                        </form>
                    </div><!-- End .header-search -->
                </div>

                <div class="header-right">
                    <div class="account">
                        <a href="user-account" title="My account">
                            <div class="icon">
                                <i class="icon-user"></i>
                            </div>
                            <p>Account</p>
                        </a>
                    </div><!-- End .compare-dropdown -->



                    <div class="dropdown cart-dropdown">
                        <a href="#" class="dropdown-toggle" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" data-display="static">
                            <div class="icon">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-calendar-check" viewBox="0 0 16 16">
                                    <path d="M10.854 7.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 9.793l2.646-2.647a.5.5 0 0 1 .708 0"/>
                                    <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5M1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4z"/>
                                </svg>
                                <span class="cart-count">2</span>
                            </div>
                            <p>Reservations</p>
                        </a>

                        <div class="dropdown-menu dropdown-menu-right">
                            <div class="dropdown-cart-products">
                                <div class="product">
                                    <div class="product-cart-details">
                                        <h4 class="product-title">
                                            <a href="viewRestaurant.jsp">Beige knitted elastic runner shoes</a>
                                        </h4>

                                        <span class="cart-product-info">
                                                <span class="cart-product-qty">1</span>
                                                x $84.00
                                            </span>
                                    </div><!-- End .product-cart-details -->

                                    <figure class="product-image-container">
                                        <a href="viewRestaurant.jsp" class="product-image">
                                            <img src="assets/images/products/cart/product-1.jpg" alt="product">
                                        </a>
                                    </figure>
                                    <a href="#" class="btn-remove" title="Remove Product"><i class="icon-close"></i></a>
                                </div><!-- End .product -->

                                <div class="product">
                                    <div class="product-cart-details">
                                        <h4 class="product-title">
                                            <a href="viewRestaurant.jsp">Blue utility pinafore denim dress</a>
                                        </h4>

                                        <span class="cart-product-info">
                                                <span class="cart-product-qty">1</span>
                                                x $76.00
                                            </span>
                                    </div><!-- End .product-cart-details -->

                                    <figure class="product-image-container">
                                        <a href="viewRestaurant.jsp" class="product-image">
                                            <img src="assets/images/products/cart/product-2.jpg" alt="product">
                                        </a>
                                    </figure>
                                    <a href="#" class="btn-remove" title="Remove Product"><i class="icon-close"></i></a>
                                </div><!-- End .product -->
                            </div><!-- End .cart-product -->

                            <div class="dropdown-cart-total">
                                <span>Total</span>

                                <span class="cart-total-price">$160.00</span>
                            </div><!-- End .dropdown-cart-total -->

                            <div class="dropdown-cart-action">
                                <a href="cart.html" class="btn btn-primary">View Cart</a>
                                <a href="checkout.html" class="btn btn-outline-primary-2"><span>Checkout</span><i class="icon-long-arrow-right"></i></a>
                            </div><!-- End .dropdown-cart-total -->
                        </div><!-- End .dropdown-menu -->
                    </div><!-- End .cart-dropdown -->
                </div><!-- End .header-right -->
            </div><!-- End .container -->
        </div><!-- End .header-middle -->

        <div class="header-bottom sticky-header">
            <div class="container">
                <div class="header-left">
                    <div class="dropdown category-dropdown">
                        <a href="#" class="dropdown-toggle" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" data-display="static" title="Browse Categories">
                            Browse Categories
                        </a>

                        <div class="dropdown-menu">
                            <nav class="side-nav">
                                <ul class="menu-vertical sf-arrows">
                                    <li class="item-lead"><a href="Restaurants.jsp">Find A Restaurant</a></li>
                                    <li class="item-lead"><a href="#">Book A Table</a></li>
                                    <li><a href="#">Your Profile</a></li>
                                    <li><a href="#"></a></li>
                                </ul><!-- End .menu-vertical -->
                            </nav><!-- End .side-nav -->
                        </div><!-- End .dropdown-menu -->
                    </div><!-- End .category-dropdown -->
                </div><!-- End .header-left -->

                <div class="header-center">
                    <nav class="main-nav">
                        <ul class="menu sf-arrows">

                            <li>
                                <a href="#" class="sf-with-ul">More On Restaurantly</a>

                                <ul>
                                    <li>
                                        <a href="about.html" class="sf-with-ul">About</a>

                                        <ul>
                                            <li><a href="about.html">About 01</a></li>
                                            <li><a href="about-2.html">About 02</a></li>
                                        </ul>
                                    </li>
                                    <li>
                                        <a href="contact.html" class="sf-with-ul">Contact</a>

                                        <ul>
                                            <li><a href="contact.html">Contact 01</a></li>
                                            <li><a href="contact-2.html">Contact 02</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="login.jsp">Login</a></li>
                                    <li><a href="faq.html">FAQs</a></li>
                                </ul>
                            </li>
                        </ul><!-- End .menu -->
                    </nav><!-- End .main-nav -->
                </div><!-- End .header-center -->

                <div class="header-right">
                    <i class="la la-lightbulb-o"></i><p>Find New Restaurants<span class="highlight">&nbsp;Up to 30% Off</span></p>
                </div>
            </div><!-- End .container -->
        </div><!-- End .header-bottom -->
    </header><!-- End .header -->

    <main class="main">
        	<div class="page-header text-center" style="background-image: url('assets/images/page-header-bg.jpg')">
        		<div class="container">
        			<h1 class="page-title">Owner Account<span>Restaurants</span></h1>
        		</div><!-- End .container -->
        	</div><!-- End .page-header -->
            <nav aria-label="breadcrumb" class="breadcrumb-nav mb-3">
                <div class="container">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
                        <li class="breadcrumb-item"><a href="#">Restaurants</a></li>
                        <li class="breadcrumb-item active" aria-current="page">My Account</li>
                    </ol>
                </div><!-- End .container -->
            </nav><!-- End .breadcrumb-nav -->

            <div class="page-content">
            	<div class="dashboard">
	                <div class="container">
	                	<div class="row">
	                		<aside class="col-md-4 col-lg-3">
	                			<ul class="nav nav-dashboard flex-column mb-3 mb-md-0" role="tablist">
								    <li class="nav-item">
								        <a class="nav-link active" id="tab-dashboard-link" data-toggle="tab" href="#tab-dashboard" role="tab" aria-controls="tab-dashboard" aria-selected="true">
                                            Dashboard</a>
								    </li>
								    <li class="nav-item">
								        <a class="nav-link" id="tab-orders-link" data-toggle="tab" href="#tab-orders" role="tab" aria-controls="tab-orders" aria-selected="false">
                                            My Restaurants</a>
								    </li>
								    <li class="nav-item">
								        <a class="nav-link" id="tab-downloads-link" data-toggle="tab" href="#tab-downloads" role="tab" aria-controls="tab-downloads" aria-selected="false">
                                            Add Restaurant</a>
								    </li>
								    <li class="nav-item">
								        <a class="nav-link" id="tab-address-link" data-toggle="tab" href="#tab-address" role="tab" aria-controls="tab-address" aria-selected="false">
                                            Update Restaurant</a>
								    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" id="tab-delete-link" data-toggle="tab" href="#tab-delete" role="tab" aria-controls="tab-address" aria-selected="false">
                                            Delete Restaurant</a>
                                    </li>
								    <li class="nav-item">
								        <a class="nav-link" id="tab-account-link" data-toggle="tab" href="#tab-account" role="tab" aria-controls="tab-account" aria-selected="false">
                                            Account Details</a>
								    </li>
								    <li class="nav-item">
								        <a class="nav-link" href="user-logout">Sign Out</a>
								    </li>
								</ul>
	                		</aside><!-- End .col-lg-3 -->

	                		<div class="col-md-8 col-lg-9">
	                			<div class="tab-content">
								    <div class="tab-pane fade show active" id="tab-dashboard" role="tabpanel" aria-labelledby="tab-dashboard-link">
								    	<p>Hello <span class="font-weight-normal text-dark"><%= session.getAttribute("name") %></span>
                                            (not <span class="font-weight-normal text-dark">User</span>? <a href="#"> </a>)
								    	<br>
								    	From your account dashboard you can view your <a href="#tab-orders" class="tab-trigger-link link-underline">recent orders</a>, manage your <a href="#tab-address" class="tab-trigger-link">shipping and billing addresses</a>, and <a href="#tab-account" class="tab-trigger-link">edit your password and account details</a>.</p>
								    </div><!-- .End .tab-pane -->
								    <div class="tab-pane fade" id="tab-orders" role="tabpanel" aria-labelledby="tab-orders-link">


                                        <%--display Resturent list--%>

                                            <%

                                            for (Restaurant restaurant : ownerAllRestaurants) {
                                                    String name = restaurant.getName();
                                                    String type = restaurant.getType();
                                                    String image = restaurant.getImageLink();
                                                    String description = restaurant.getDescription();

                                            %>

                                            <div class="product product-list">
                                                <div class="row">
                                                    <div class="col-6 col-lg-3">
                                                        <figure class="product-media">
                                                            <a href="viewRestaurant.jsp">
                                                                <img src="<%= image %>" alt="Product image" class="product-image">
                                                            </a>
                                                        </figure><!-- End .product-media -->
                                                    </div><!-- End .col-sm-6 col-lg-3 -->
                                                    <div class="col-lg-6">
                                                        <div class="product-body product-action-inner">
                                                            <div class="product-cat">
                                                                <a href="#"><%= type %></a>
                                                            </div><!-- End .product-cat -->
                                                            <h3 class="product-title"><a href="viewRestaurant.jsp"><%= name %></a></h3><!-- End .product-title -->

                                                            <div class="product-content">
                                                                <p><%= description %> </p>
                                                                <div class="col-6 col-lg-3 order-lg-last">
                                                                    <div class="product-list-action">
                                                                        <a href="tableReservations.jsp?name=<%=name%>" class="btn-product btn-cart"><span>View</span></a>
                                                                    </div><!-- End .product-list-action -->
                                                                </div><!-- End .col-sm-6 col-lg-3 -->
                                                            </div><!-- End .product-content -->
                                                        </div><!-- End .product-body -->
                                                    </div><!-- End .col-lg-6 -->
                                                </div><!-- End .row -->
                                            </div><!-- End .product -->

                                            <%
                                                }
                                            %>





                                        <%--end display Resturent --%>
								    </div><!-- .End .tab-pane -->
                                    <%--Restaurent Register Form--%>
								    <div class="tab-pane fade" id="tab-downloads" role="tabpanel" aria-labelledby="tab-downloads-link">
								    	<form action="register-restaurant" method="post">
		            						<label>Restaurant Name *</label>
		            						<input name="restaurant-name" type="text" class="form-control" required>
		            						<small class="form-text">This will be how your name will be displayed in the account section and in reviews</small>
                                            
                                            <label>Restaurant Type *</label>
		            						<input name="restaurant-type" type="text" class="form-control" required>
		            						<small class="form-text">Casual dining/Family restaurant/Diner/Bakery/Buffet/Fine dining</small>

                                            <label>Restaurant Image Link *</label>
		            						<input name="restaurant-image" type="text" class="form-control" required>

                                            <label>Restaurant Description *</label>
		            						<textarea name="restaurant-description" class="form-control"></textarea>
		                					<button type="submit" class="btn btn-outline-primary-2">
			                					<span>SAVE CHANGES</span>
			            						<i class="icon-long-arrow-right"></i>
			                				</button>
			                			</form>
								    </div>
                                    <!-- .End restaurant register -->
								    <div class="tab-pane fade" id="tab-address" role="tabpanel" aria-labelledby="tab-address-link">
								    	<p>The following addresses will be used on the checkout page by default.</p>

								    	<form action="update-restaurant" method="post">
		            						<label>Current Restaurant Name *</label>
		            						<input name="name" type="text" class="form-control" required>
		            						<small class="form-text">^ This restaurants data will be updated using the below values.</small>

                                            <label>Restaurant Name *</label>
		            						<input name="newName" type="text" class="form-control" required>
                                            
                                            <label>Restaurant Type *</label>
		            						<input name="type" type="text" class="form-control" required>
		            						<small class="form-text">Casual dining/Family restaurant/Diner/Bakery/Buffet/Fine dining</small>

                                            <label>Restaurant Image Link *</label>
		            						<input name="link" type="text" class="form-control" required>

                                            <label>Restaurant Description </label>
		            						<textarea name="description" class="form-control"></textarea>
		                					<button type="submit" class="btn btn-outline-primary-2">
			                					<span>SAVE CHANGES</span>
			            						<i class="icon-long-arrow-right"></i>
			                				</button>
			                			</form>
								    </div><!-- .End .tab-pane -->
								    <div class="tab-pane fade" id="tab-account" role="tabpanel" aria-labelledby="tab-account-link">
								    	<form action="update-user" method="post">

		            						<label>Display Name *</label>
		            						<input name="name" type="text" class="form-control" required value="<%= owner.getDisplayName() %>">
		            						<small class="form-text">This will be how your name will be displayed in the account section and in reviews</small>

		                					<label>Email address *</label>
		        							<input name="email" type="email" class="form-control" required value="<%= owner.getEmail() %>">

		            						<label>Current password</label>
		            						<input name="oldPassword" type="password" class="form-control" value="<%= owner.getPassword() %>">

		            						<label>New password</label>
		            						<input name="newPassword1" type="password" class="form-control">

		            						<label>Confirm new password</label>
		            						<input name="newPassword2" type="password" class="form-control mb-2">

		                					<button type="submit" class="btn btn-outline-primary-2">
			                					<span>SAVE CHANGES</span>
			            						<i class="icon-long-arrow-right"></i>
			                				</button>
			                			</form>
								    </div><!-- .End .tab-pane -->
                                    <div class="tab-pane fade" id="tab-delete" role="tabpanel" aria-labelledby="tab-delete-link">
                                        <p>The following addresses will be used on the checkout page by default.</p>

                                        <form action="restaurant-delete" method="post">
                                            <label>Restaurant Name *</label>
                                            <input type="text" class="form-control" name="name" required>
                                            <small class="form-text">^ This restaurant will be deleted.</small>
                                            <button type="submit" class="btn btn-outline-danger">
                                                <span>DELETE !</span>
                                                <i class="icon-long-arrow-right"></i>
                                            </button>
                                        </form>
                                    </div><!-- .End .tab-pane -->

                                </div>
	                		</div><!-- End .col-lg-9 -->
	                	</div><!-- End .row -->
	                </div><!-- End .container -->
                </div><!-- End .dashboard -->
            </div><!-- End .page-content -->
        </main><!-- End .main -->
        <footer class="footer">
        	<div class="footer-middle">
	            <div class="container">
	            	<div class="row">
	            		<div class="col-sm-6 col-lg-3">
	            			<div class="widget widget-about">
	            				<img src="assets/images/logo.png" class="footer-logo" alt="Footer Logo" width="105" height="25">
	            				<p>Praesent dapibus, neque id cursus ucibus, tortor neque egestas augue, eu vulputate magna eros eu erat. </p>

	            				<div class="social-icons">
	            					<a href="#" class="social-icon" target="_blank" title="Facebook"><i class="icon-facebook-f"></i></a>
	            					<a href="#" class="social-icon" target="_blank" title="Twitter"><i class="icon-twitter"></i></a>
	            					<a href="#" class="social-icon" target="_blank" title="Instagram"><i class="icon-instagram"></i></a>
	            					<a href="#" class="social-icon" target="_blank" title="Youtube"><i class="icon-youtube"></i></a>
	            					<a href="#" class="social-icon" target="_blank" title="Pinterest"><i class="icon-pinterest"></i></a>
	            				</div><!-- End .soial-icons -->
	            			</div><!-- End .widget about-widget -->
	            		</div><!-- End .col-sm-6 col-lg-3 -->

	            		<div class="col-sm-6 col-lg-3">
	            			<div class="widget">
	            				<h4 class="widget-title">Useful Links</h4><!-- End .widget-title -->

	            				<ul class="widget-list">
	            					<li><a href="about.html">About Molla</a></li>
	            					<li><a href="#">How to shop on Molla</a></li>
	            					<li><a href="#">FAQ</a></li>
	            					<li><a href="contact.html">Contact us</a></li>
	            					<li><a href="login.jsp">Log in</a></li>
	            				</ul><!-- End .widget-list -->
	            			</div><!-- End .widget -->
	            		</div><!-- End .col-sm-6 col-lg-3 -->

	            		<div class="col-sm-6 col-lg-3">
	            			<div class="widget">
	            				<h4 class="widget-title">Customer Service</h4><!-- End .widget-title -->

	            				<ul class="widget-list">
	            					<li><a href="#">Payment Methods</a></li>
	            					<li><a href="#">Money-back guarantee!</a></li>
	            					<li><a href="#">Returns</a></li>
	            					<li><a href="#">Shipping</a></li>
	            					<li><a href="#">Terms and conditions</a></li>
	            					<li><a href="#">Privacy Policy</a></li>
	            				</ul><!-- End .widget-list -->
	            			</div><!-- End .widget -->
	            		</div><!-- End .col-sm-6 col-lg-3 -->

	            		<div class="col-sm-6 col-lg-3">
	            			<div class="widget">
	            				<h4 class="widget-title">My Account</h4><!-- End .widget-title -->

	            				<ul class="widget-list">
	            					<li><a href="#">Sign In</a></li>
	            					<li><a href="cart.html">View Cart</a></li>
	            					<li><a href="#">My Wishlist</a></li>
	            					<li><a href="#">Track My Order</a></li>
	            					<li><a href="#">Help</a></li>
	            				</ul><!-- End .widget-list -->
	            			</div><!-- End .widget -->
	            		</div><!-- End .col-sm-6 col-lg-3 -->
	            	</div><!-- End .row -->
	            </div><!-- End .container -->
	        </div><!-- End .footer-middle -->

	        <div class="footer-bottom">
	        	<div class="container">
	        		<p class="footer-copyright">Copyright © 2019 Molla Store. All Rights Reserved.</p><!-- End .footer-copyright -->
	        		<figure class="footer-payments">
	        			<img src="assets/images/payments.png" alt="Payment methods" width="272" height="20">
	        		</figure><!-- End .footer-payments -->
	        	</div><!-- End .container -->
	        </div><!-- End .footer-bottom -->
        </footer><!-- End .footer -->
    </div><!-- End .page-wrapper -->
    <button id="scroll-top" title="Back to Top"><i class="icon-arrow-up"></i></button>
    <!-- Mobile Menu -->
    <div class="mobile-menu-overlay"></div><!-- End .mobil-menu-overlay -->
    <div class="mobile-menu-container">
        <div class="mobile-menu-wrapper">
            <span class="mobile-menu-close"><i class="icon-close"></i></span>

            <form action="#" method="get" class="mobile-search">
                <label for="mobile-search" class="sr-only">Search</label>
                <input type="search" class="form-control" name="mobile-search" id="mobile-search" placeholder="Search in..." required>
                <button class="btn btn-primary" type="submit"><i class="icon-search"></i></button>
            </form>
            
            <nav class="mobile-nav">
                <ul class="mobile-menu">
                    <li class="active">
                        <a href="index.jsp">Home</a>

                        <ul>
                            <li><a href="index-1.html">01 - furniture store</a></li>
                            <li><a href="index-2.html">02 - furniture store</a></li>
                            <li><a href="index-3.html">03 - electronic store</a></li>
                            <li><a href="index-4.html">04 - electronic store</a></li>
                            <li><a href="index-5.html">05 - fashion store</a></li>
                            <li><a href="index-6.html">06 - fashion store</a></li>
                            <li><a href="index-7.html">07 - fashion store</a></li>
                            <li><a href="index-8.html">08 - fashion store</a></li>
                            <li><a href="index-9.html">09 - fashion store</a></li>
                            <li><a href="index-10.html">10 - shoes store</a></li>
                            <li><a href="index-11.html">11 - furniture simple store</a></li>
                            <li><a href="index-12.html">12 - fashion simple store</a></li>
                            <li><a href="index-13.html">13 - market</a></li>
                            <li><a href="index-14.html">14 - market fullwidth</a></li>
                            <li><a href="index-15.html">15 - lookbook 1</a></li>
                            <li><a href="index-16.html">16 - lookbook 2</a></li>
                            <li><a href="index-17.html">17 - fashion store</a></li>
                            <li><a href="index-18.html">18 - fashion store (with sidebar)</a></li>
                            <li><a href="index-19.html">19 - games store</a></li>
                            <li><a href="index-20.html">20 - book store</a></li>
                            <li><a href="index-21.html">21 - sport store</a></li>
                            <li><a href="index-22.html">22 - tools store</a></li>
                            <li><a href="index-23.html">23 - fashion left navigation store</a></li>
                            <li><a href="index-24.html">24 - extreme sport store</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="category.html">Shop</a>
                        <ul>
                            <li><a href="category-list.html">Shop List</a></li>
                            <li><a href="category-2cols.html">Shop Grid 2 Columns</a></li>
                            <li><a href="category.html">Shop Grid 3 Columns</a></li>
                            <li><a href="category-4cols.html">Shop Grid 4 Columns</a></li>
                            <li><a href="category-boxed.html"><span>Shop Boxed No Sidebar<span class="tip tip-hot">Hot</span></span></a></li>
                            <li><a href="category-fullwidth.html">Shop Fullwidth No Sidebar</a></li>
                            <li><a href="product-category-boxed.html">Product Category Boxed</a></li>
                            <li><a href="product-category-fullwidth.html"><span>Product Category Fullwidth<span class="tip tip-new">New</span></span></a></li>
                            <li><a href="cart.html">Cart</a></li>
                            <li><a href="checkout.html">Checkout</a></li>
                            <li><a href="wishlist.html">Wishlist</a></li>
                            <li><a href="#">Lookbook</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="viewRestaurant.jsp" class="sf-with-ul">Product</a>
                        <ul>
                            <li><a href="viewRestaurant.jsp">Default</a></li>
                            <li><a href="product-centered.html">Centered</a></li>
                            <li><a href="product-extended.html"><span>Extended Info<span class="tip tip-new">New</span></span></a></li>
                            <li><a href="product-gallery.html">Gallery</a></li>
                            <li><a href="product-sticky.html">Sticky Info</a></li>
                            <li><a href="product-sidebar.html">Boxed With Sidebar</a></li>
                            <li><a href="product-fullwidth.html">Full Width</a></li>
                            <li><a href="product-masonry.html">Masonry Sticky Info</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">Pages</a>
                        <ul>
                            <li>
                                <a href="about.html">About</a>

                                <ul>
                                    <li><a href="about.html">About 01</a></li>
                                    <li><a href="about-2.html">About 02</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="contact.html">Contact</a>

                                <ul>
                                    <li><a href="contact.html">Contact 01</a></li>
                                    <li><a href="contact-2.html">Contact 02</a></li>
                                </ul>
                            </li>
                            <li><a href="login.jsp">Login</a></li>
                            <li><a href="faq.html">FAQs</a></li>
                            <li><a href="404.html">Error 404</a></li>
                            <li><a href="coming-soon.html">Coming Soon</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="blog.html">Blog</a>

                        <ul>
                            <li><a href="blog.html">Classic</a></li>
                            <li><a href="blog-listing.html">Listing</a></li>
                            <li>
                                <a href="#">Grid</a>
                                <ul>
                                    <li><a href="blog-grid-2cols.html">Grid 2 columns</a></li>
                                    <li><a href="blog-grid-3cols.html">Grid 3 columns</a></li>
                                    <li><a href="blog-grid-4cols.html">Grid 4 columns</a></li>
                                    <li><a href="blog-grid-sidebar.html">Grid sidebar</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="#">Masonry</a>
                                <ul>
                                    <li><a href="blog-masonry-2cols.html">Masonry 2 columns</a></li>
                                    <li><a href="blog-masonry-3cols.html">Masonry 3 columns</a></li>
                                    <li><a href="blog-masonry-4cols.html">Masonry 4 columns</a></li>
                                    <li><a href="blog-masonry-sidebar.html">Masonry sidebar</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="#">Mask</a>
                                <ul>
                                    <li><a href="blog-mask-grid.html">Blog mask grid</a></li>
                                    <li><a href="blog-mask-masonry.html">Blog mask masonry</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="#">Single Post</a>
                                <ul>
                                    <li><a href="single.html">Default with sidebar</a></li>
                                    <li><a href="single-fullwidth.html">Fullwidth no sidebar</a></li>
                                    <li><a href="single-fullwidth-sidebar.html">Fullwidth with sidebar</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="elements-list.html">Elements</a>
                        <ul>
                            <li><a href="elements-products.html">Products</a></li>
                            <li><a href="elements-typography.html">Typography</a></li>
                            <li><a href="elements-titles.html">Titles</a></li>
                            <li><a href="elements-banners.html">Banners</a></li>
                            <li><a href="elements-product-category.html">Product Category</a></li>
                            <li><a href="elements-video-banners.html">Video Banners</a></li>
                            <li><a href="elements-buttons.html">Buttons</a></li>
                            <li><a href="elements-accordions.html">Accordions</a></li>
                            <li><a href="elements-tabs.html">Tabs</a></li>
                            <li><a href="elements-testimonials.html">Testimonials</a></li>
                            <li><a href="elements-blog-posts.html">Blog Posts</a></li>
                            <li><a href="elements-portfolio.html">Portfolio</a></li>
                            <li><a href="elements-cta.html">Call to Action</a></li>
                            <li><a href="elements-icon-boxes.html">Icon Boxes</a></li>
                        </ul>
                    </li>
                </ul>
            </nav><!-- End .mobile-nav -->

            <div class="social-icons">
                <a href="#" class="social-icon" target="_blank" title="Facebook"><i class="icon-facebook-f"></i></a>
                <a href="#" class="social-icon" target="_blank" title="Twitter"><i class="icon-twitter"></i></a>
                <a href="#" class="social-icon" target="_blank" title="Instagram"><i class="icon-instagram"></i></a>
                <a href="#" class="social-icon" target="_blank" title="Youtube"><i class="icon-youtube"></i></a>
            </div><!-- End .social-icons -->
        </div><!-- End .mobile-menu-wrapper -->
    </div><!-- End .mobile-menu-container -->
    <!-- Plugins JS File -->
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/bootstrap.bundle.min.js"></script>
    <script src="assets/js/jquery.hoverIntent.min.js"></script>
    <script src="assets/js/jquery.waypoints.min.js"></script>
    <script src="assets/js/superfish.min.js"></script>
    <script src="assets/js/owl.carousel.min.js"></script>
    <!-- Main JS File -->
    <script src="assets/js/main.js"></script>
</body>


<!-- molla/dashboard.html  22 Nov 2019 10:03:13 GMT -->
</html>