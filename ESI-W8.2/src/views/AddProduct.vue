<template>
  <div class="form">
    <h3>Add a Product</h3>

    <label for="id">ID: </label>
    <input name="id" type="text" id="id" required v-model="product.id" />

    <label for="code">Code: </label>
    <input name="code" type="text" id="code" required v-model="product.code" />

    <label for="name">Name: </label>
    <input name="name" type="text" id="name" required v-model="product.name" />

    <label for="description">Description: </label>
    <input name="description"  type="text" id="description" required v-model="product.description"/>

    <label for="price">Price: </label>
    <input name="price"  type="number" id="price" required v-model="product.price"/>

    <button @click="addProduct" class="addPost">Add Product</button>
  </div>
</template>

<script>
export default {
  name: "AddProduct",
  data() {
    return {
      product: {
        id: "",
        code: "",
        name: "",
        description: "",
        price: 0.0,
      },
    };
  },
  methods: {
    addProduct() {
      var data = {
        id: this.product.id,
        code: this.product.code,
        name: this.product.name,
        description: this.product.description,
        price: this.product.price,
      };
      // using Fetch - post method - send an HTTP post request to the specified URI with the defined body
      fetch("http://localhost:8082/api/products", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      })
      .then((response) => {
        console.log(response.data);
        // redirect to /allposts view
        this.$router.push("/api/allposts");
      })
      .catch((e) => {
        console.log(e);
        console.log("error");
      });
    },
  },
};
</script>

<style scoped>
.form {
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
  align-items: center;
  text-align: center;
}
</style>