package com.shankar.model;

import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Product
 * <p>
 * A product
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "code", "name", "price", "tags" })
public class Product {

	/**
	 * The unique identifier for a product, autogenerated by the api
	 * 
	 */
	@JsonProperty("code")
	@JsonPropertyDescription("")
	private String code;
	/**
	 * Name of the product (Required)
	 * 
	 */
	@JsonProperty("name")
	@JsonPropertyDescription("")
	private String name;
	@JsonProperty("price")
	private Double price;
	@JsonProperty("tags")
	@JsonDeserialize(as = java.util.LinkedHashSet.class)
	private Set<String> tags = null;

	/**
	 * The unique identifier for a product, autogenerated by the api
	 * 
	 * @return The code
	 */
	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	/**
	 * The unique identifier for a product, autogenerated by the api
	 * 
	 * @param code
	 *            The code
	 */
	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Name of the product (Required)
	 * 
	 * @return The name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * Name of the product (Required)
	 * 
	 * @param name
	 *            The name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return The price
	 */
	@JsonProperty("price")
	public Double getPrice() {
		return price;
	}

	/**
	 * 
	 * @param price
	 *            The price
	 */
	@JsonProperty("price")
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * 
	 * @return The tags
	 */
	@JsonProperty("tags")
	public Set<String> getTags() {
		return tags;
	}

	/**
	 * 
	 * @param tags
	 *            The tags
	 */
	@JsonProperty("tags")
	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Product product = (Product) o;
		return Objects.equals(this.code, product.getCode());
	}

	@Override
	public int hashCode() {
		return Objects.hash(code);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Product {\n");
		sb.append("    code: ").append(toIndentedString(code));
		sb.append("    name: ").append(toIndentedString(name));
		sb.append("    price: ").append(toIndentedString(price));
		sb.append("\n");
		sb.append("}");

		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

}