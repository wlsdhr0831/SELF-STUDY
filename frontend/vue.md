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

```jsx
<template>
		<div id="app">
		<router-link to="/home">Home</router-link>
		<router-view></router-view>
	</div>
</template>
```

```jsx
import { router} from './routes/index.js';

new Vue({
	render: h => h(App),
	router,
}).$mount('#app');
```

## 3) path 사용

```jsx
import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';

Vue.use(VueRouter);

export const router = new VueRouter({
	mode: 'history', => "#"값 제거
	routes: [
		{
			path: '/user/:id',
			component: User,
			name: 'User',
		},
	]
});
```

```jsx
<template>
	<div>
		<router-link to="`/user/${user.name}`">{{ user.name }}</router-link>
	</div>
</template>

<script>
export default {
	data() {
		return {
			user: {
				name: 'name',
			}
		}
	}
}
</script>
```

```jsx
<template>
	<div>{{ name }}</div>
</template>

<script>
export default {
	data() {
		return {
			name: '',
		}
	},
	created(){
		this.name = this.$route.params.id;
	},
}
</script>
```