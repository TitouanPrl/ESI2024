<template>
  <div class="A Post">
    <div id="form">
      <h3>A Product</h3>

      <label for="code">Code: </label>
      <input name="code" type="text" id="code" required v-model="product.code" />
      
      <label for="name">Name: </label>
      <input name="name" type="text" id="name" required v-model="product.name" />
      
      <label for="description">Description: </label>
      <input name="description" type="text" id="description" required v-model="product.description" />

      <label for="price">Price: </label>
      <input name="price" type="text" id="price" required v-model="product.price" />

    </div>
    <div>
      <button @click="updateProduct" class="updateProduct">Update Product</button>
      <button @click="deleteProduct" class="deleteProduct">Delete Product</button>
    </div>
  </div>
</template>


<script>
export default {
  name: "AProduct",
  data() {
    return {
      product: {
        code: "",
        name: "",
        description: "",
        price: "",
      },
    };
  },
  methods: {
    fetchProduct(id) {
      // fetch one product with the specified  id (id)
      fetch(`http://localhost:8082/api/products/${id}`)
        .then((response) => response.json())
        .then((data) => (this.product = data))
        .catch((err) => console.log(err.message));
    },
    updateProduct() {
      // using Fetch - put method - updates a specific product based on the passed id and the specified body
      fetch(`http://localhost:8082/api/products/${this.product.id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(this.product),
      })
        .then((response) => {
          console.log(response.data);
          //this.$router.push("/apost/" + this.product.id);
          // We are using the router instance of this element to navigate to a different URL location
          this.$router.push("/api/allproducts");
        })
        .catch((e) => {
          console.log(e);
        });
    },
    deleteProduct() {
    },
  },
  mounted() {
    // call fetchAPost() when this element mounts, and pass to it a route parameter  (id)
    // Route parameters (this.$route.params.id) are named URL segments that are used to capture the values specified at their 
    // position in the URL. The captured values are populated in the req.params object, with the name 
    // of the route parameter specified in the path as their respective keys
    this.fetchProduct(this.$route.params.id);
  },
};
</script>

<style scoped>
#form {
  max-width: 420px;
  margin: 30px auto;
  background: rgb(167, 154, 154);
  text-align: left;
  padding: 40px;
  border-radius: 10px;
}
h3 {
  text-align: center;
  color: rgb(8, 110, 110);
}
label {
  color: rgb(8, 110, 110);
  display: inline-block;
  margin: 25px 0 15px;
  font-size: 0.8em;
  text-transform: uppercase;
  letter-spacing: 1px;
  font-weight: bold;
}
input {
  display: block;
  padding: 10px 6px;
  width: 100%;
  box-sizing: border-box;
  border: none;
  border-bottom: 1px solid white;
  color: blue;
}
button {
  background: rgb(8, 110, 110);
  border: 0;
  padding: 10px 20px;
  margin-top: 20px;
  color: white;
  border-radius: 20px;
}
</style>