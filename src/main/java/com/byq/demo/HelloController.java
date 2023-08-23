package com.byq.demo;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("byq")
public class HelloController {
	@RequestMapping(value="hello1", method = RequestMethod.PUT)
	public Date hello1() {
		return new Date();
	}
	@GetMapping(value="hello1")

	public String helloPost() {
		return  "hello worldPost!";
	}
	@RequestMapping("hello2")
	public String hello2(@RequestBody Person person) {
		return  " hello world2!";
	}
	
	@GetMapping(value="/fetch/{id}")
	public String getX(@PathVariable String id) {
		return id;
	}

}
class Person{
	String name;
	int age;
	String[] address;
	int sex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String[] getAddress() {
		return address;
	}
	public void setAddress(String[] address) {
		this.address = address;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
}
