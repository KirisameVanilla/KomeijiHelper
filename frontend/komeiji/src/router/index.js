// src/router/index.js
import { createRouter, createWebHistory } from "vue-router";
import LoginView from "@/views/LoginView.vue";
import AboutView from "@/views/AboutView.vue";
import HomeView from "@/views/HomeView.vue";
import SelectAssistantView from "@/views/SelectAssistantView.vue";

const routes = [
    {
        path: "/",
        name: "Home",
        component: HomeView,
    },
    {
        path: "/login",
        name: "Login",
        component: LoginView,
    },
    {
        path: "/about",
        name: "About",
        component: AboutView,
    },
    {
        path: "/select",
        name: "Select",
        component: SelectAssistantView,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
