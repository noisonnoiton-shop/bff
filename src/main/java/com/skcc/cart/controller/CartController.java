package com.skcc.cart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.skcc.cart.domain.Cart;
import com.skcc.cart.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
// @XRayEnabled
public class CartController {

	private CartService cartService;
	
	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@GetMapping(value="/carts/account/{accountId}")
	public List<Cart> getCartsByAccountId(@PathVariable long accountId) {
		return this.cartService.getCartsByAccountId(accountId);
	}
	
	@PutMapping(value="/carts")
	public boolean addCart(HttpServletRequest request, @RequestBody Cart cart) throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("username") == null) {
			throw new Exception();
		}
		
		return cartService.addCart((long) session.getAttribute("id"), cart);
	}
	
	@DeleteMapping(value="/carts/{id}")
	public void deleteCart(@PathVariable long id) {
		this.cartService.deleteCart(id);
	}
	
}
