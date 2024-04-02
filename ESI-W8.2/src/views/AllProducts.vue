<template>
  <div class="AllProducts">
    <div id="products-list">
    <h1>All Products</h1>
      <ul>
        <div class="item" v-for="product in products" :key="product.id">
          <!--  We are putting an anchor for each product, when we click on it, we will be directed to the specific product view (/aproduct/) /  -->
          <a class="singleproduct" :href="'/api/aproduct/' + product.id">
            <span class="code"> <b>Code:</b> {{ product.code }} </span><br />
            <span class="name"> <b>Name:</b> {{ product.name }} </span> <br />
            <span class="description"> <b>Description:</b> {{ product.description }} </span> <br />
            <span class="price"> <b>Price:</b> {{ product.price }} </span> <br />
          </a>
        </div>
      </ul>
    </div>
  </div>
</template>


<script>
export default {
  name: "AllProducts",
  data() {
    return {
      products: [],
    };
  },
  methods: {
    fetchProducts() {
      // fetch is a GET request by default unless stated otherwise. Therefore, it will fetch all products from the database
      fetch(`http://localhost:8082/api/products`)
        .then((response) => response.json())
        .then((data) => (this.products = data))
        .catch((err) => console.log(err.message));
    },
  },
  mounted() {
    // call fetchProducts() when this element (AllProducts()) mounts 
    this.fetchProducts();
    console.log("mounted");
  },
};
</script>

<style scoped>
h1 {
  font-size: 20px;
}
a {
  text-decoration: none;
}
a:hover {
  text-decoration: underline;
}
.item {
  background: rgb(189, 212, 199);
  margin-bottom: 5px;
  padding: 3px 5px;
  border-radius: 10px;
}
#post-list {
  background: #6e8b97;
  box-shadow: 1px 2px 3px rgba(0, 0, 0, 0.2);
  margin-bottom: 30px;
  padding: 10px 20px;
  margin: auto;
  width: 50%;
  border-radius: 20px;
}
#post-list ul {
  padding: 0;
}
#post-list li {
  display: inline-block;
  margin-right: 10px;
  margin-top: 10px;
  padding: 20px;
  background: rgba(255, 255, 255, 0.7);
}
</style>