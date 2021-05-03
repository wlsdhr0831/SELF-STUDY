# 1. Router

## 1) 개념

## 2) 적용 방법

```jsx
import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';

Vue.use(VueRouter);

export const router = new VueRouter({
	mode: 'history', => "#"값 제거
	routes: [
		{
			path: '/',
			redirect: '/home',
		},
		{
			path: '/home',
			component: Home,
			name: 'Home',
		},
	]
});
```