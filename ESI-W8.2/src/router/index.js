import { createRouter, createWebHistory } from 'vue-router'

import AllProducts from "../views/AllProducts.vue";
import AProduct from "../views/AProduct.vue";
import AddProduct from "../views/AddProduct.vue";



const routes = [{
        path: '/',
        name: 'AllProducts',
        component: AllProducts,
    },
    {
        path: "/api/allproducts",
        name: "AllProducts",
        component: AllProducts,
    },
    {
        path: "/api/aproduct/:id",
        name: "AProduct",
        component: AProduct,
    },
    {
        path: "/api/addproduct",
        name: "AddProduct",
        component: AddProduct,
    },
    { //will route to AllPosts view if none of the previous routes apply
        path: "/:catchAll(.*)",
        name: "AllProducts",
        component: AllProducts,
    }
]


const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router