# 1. 설치 방법

```jsx
npm install vuex
```

# 2. 적용 방법

```jsx
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

new Vuex.Store({
	state,
	getters,
	mutations,
	actions: 
});
```

```jsx
import { store } from './store/index.js';

new Vue({
	render: h => h(App),
	store,
}).$mount('#app');
```

# 3. state

```jsx
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

new Vuex.Store({
	state: {
		user: {},
		board: [],
	},
});
```

# 4. dispatch, actions, mutations

```jsx
<template>
	<div>{{ this.$store.state.user }}</div>
</template>

<script>
export default {
	created() {
		this.$store.dispatch('GET_USER_INFO');
	}
}
<sciprt>
```

```jsx
import Vue from 'vue';
import Vuex from 'vuex';
import { getUserInfo } from '../api/user.js';

Vue.use(Vuex);

new Vuex.Store({
	state: {
		user: {}
	},
	mutations: {
		SET_USER(state, payload) {
			state.user = payload;
		}
	},
	actions: {
		GET_USER_INFO({ commit }) {
			getUserInfo()
				.then(({ data })=> {
					commit('SET_USER', data);
				})
				.catch( e => {
					console.log(e);
				})
		}
	}
});
```

# 5. getters

```jsx
import Vue from 'vue';
import Vuex from 'vuex';
import { getUserInfo } from '../api/user.js';

Vue.use(Vuex);

new Vuex.Store({
	state: {
		user: {}
	},
	getters: {
		user(state) {
			return state.user;
		}
	},
	mutations: {
		SET_USER(state, payload) {
			state.user = payload;
		}
	},
	actions: {
		GET_USER_INFO({ commit }) {
			getUserInfo()
				.then(({ data })=> {
					commit('SET_USER', data);
				})
				.catch( e => {
					console.log(e);
				})
		}
	},
});
```

```jsx
<template>
	<div>{{ this.$store.state.user }}</div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
	computed: {
		// state 값 이름 변화
		...mapGetters({
			user: 'user',
		}),
		// state 값 이름 유지
		...mapGetters([
			'user',
		]),
	}
}
<sciprt>
```

# 6. map helper function

```jsx
<template>
	<div>{{ user }}</div>
</template>

<script>
import { mapState } from 'vuex';

export default {
	computed: {
		...mapState({
			user: state => state.user,
		});
	},
	created() {
		this.$store.dispatch('GET_USER_INFO');
	}
}
<sciprt>
```

# 7. convention

```jsx
<template>
	<div>{{ user }}</div>
</template>

<script>
export default {
	computed: {
		user() {
			return this.$store.state.user;
		}
	},
	created() {
		this.$store.dispatch('GET_USER_INFO');
	}
}
<sciprt>
```