# 1️⃣ Redux-middleware

액션 객체가 리듀서에서 처리되기 전에 우리가 원하는 작업들을 수행 할 수 있습니다.

- 특정 조건에 따라 액션이 무시
- 액션을 콘솔에 출력, 서버쪽에 로깅
- 액션이 디스패치 됐을 때 이를 수정 후 리듀서에게 전달
- 특정 액션이 발생했을 때 이에 기반하여 다른 액션이 발생
- 특정 액션이 발생했을 때 특정 자바스크립트 함수를 실행

미들웨어는 주로 **비동기 작업**을 처리 할 때 많이 사용됩니다.

## template

```jsx
const middleware = store => next => action => {
  // 하고 싶은 작업...
}
```

```jsx
function middleware(store) {
  return function (next) {
    return function (action) {
      // 하고 싶은 작업...
    };
  };
};
```

## store

- dispatch, getState, subscribe 내장 함수 존재
- store.dispatch : 다른 액션을 추가적으로 발생시킴

## next

- 액션을 다음 미들웨어 / 리듀서에게 전달
- 호출안하면 액션이 무시 처리됨
- next(action) 으로 사용

## action

- 현재 처리하고 있는 액션 객체

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7d4f8e99-ba62-4f5f-8006-42c901bee968/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7d4f8e99-ba62-4f5f-8006-42c901bee968/Untitled.png)

# 2️⃣ redux-logger

- library 설치 후 index.js 에서 store 생성 시 값 넘겨주기
- 객체와 상태값을 콘솔에 찍어주는 라이브러리

    ⇒ redux-devtools 로 대체 가능

# 3️⃣ redux-thunk

```jsx
function createThunkMiddleware(extraArgument) {
  return ({ dispatch, getState }) => next => action => {
    if (typeof action === 'function') {
      return action(dispatch, getState, extraArgument);
    }

    return next(action);
  };
}

const thunk = createThunkMiddleware();
thunk.withExtraArgument = createThunkMiddleware;

export default thunk;
```

```jsx
const thunk = store => next => action =>
  typeof action === 'function'
    ? action(store.dispatch, store.getState) // 액션 함수 실행
    : next(action) // 다음 미들웨어 (또는 리듀서) 에게 액션을 전달합니다.
```

```jsx
const myThunk = () => (dispatch, getState) => {
  dispatch({ type: 'HELLO' });
  dispatch({ type: 'BYE' });
}

dispatch(myThunk());
```

## thunk란?

- 특정 작업을 나중에 하도록 미루기 위해서 함수 형태로 감싼 것

```jsx
const foo = () => 1 + 2;
```

- 리덕스에서 비동기 작업을 처리 할 때 가장 많이 사용하는 미들웨어
- 액션 객체가 아닌 액션 생성함수를 디스패치 가능
- 특정 액션이 몇 초 뒤에 실행되게 하거나 현재 상태에 따라 액션을 무시하게 하는 행동 가능

    ```jsx
    const INCREMENT_COUNTER = 'INCREMENT_COUNTER';

    function increment() {
      return {
        type: INCREMENT_COUNTER
      };
    }

    function incrementAsync() {
      return dispatch => { // dispatch 를 파라미터로 가지는 함수를 리턴합니다.
        setTimeout(() => {
          // 1 초뒤 dispatch 합니다
          dispatch(increment());
        }, 1000);
      };
    }

    store.dispatch(incrementAsync());
    => 이렇게 사용하면 결과적으로 increment 액션 객체 생성 함수가 1초 뒤에 디스패치 됨

    function incrementIfOdd() {
      return (dispatch, getState) => {
        const { counter } = getState();

        if (counter % 2 === 0) {
          return;
        }

        dispatch(increment());
      };
    }

    store.dispatch(incrementIfOdd());
    => counter가 짝수/홀수 인지에 따라 액션을 increment 액션 객체 생성함수의 실행 유무 결정
    ```

- thunk 함수

    : 함수를 디스패치 할 때에는, 해당 함수에서 dispatch 와 getState 를 파라미터로 받아와야함.

    : 이 함수를 만들어주는 함수

    ```jsx
    const getComments = () => (dispatch, getState) => {
      const id = getState().post.activeId;
      dispatch({ type: 'GET_COMMENTS' });

      // 댓글을 조회하는 프로미스를 반환하는 getComments 가 있다고 가정해봅시다.
      api
        .getComments(id) // 요청을 하고
        .then(comments => dispatch({ type: 'GET_COMMENTS_SUCCESS', id, comments })) // 성공시
        .catch(e => dispatch({ type: 'GET_COMMENTS_ERROR', error: e })); // 실패시
    };
    ```

    ```jsx
    const getComments = () => async (dispatch, getState) => {
      const id = getState().post.activeId;
      dispatch({ type: 'GET_COMMENTS' });

    	// async / await 사용
      try {
        const comments = await api.getComments(id);
        dispatch({ type:  'GET_COMMENTS_SUCCESS', id, comments });
      } catch (e) {
        dispatch({ type:  'GET_COMMENTS_ERROR', error: e });
      }
    }
    ```

# 4️⃣ redux-saga

- 액션을 모니터링 하고있다가 특정 액션이 발생하면 이에 따라 제너레이터 함수를 실행하여 비동기 작업 처리 후액션을 모니터링 하고있다가 특정 액션이 발생하면 이에 따라 제너레이터 함수를 실행하여 비동기 작업 처리 후 특정작업을 하는 방식으로 사용

    ⇒ js 실행, 다른 액션 디스패치, 현재 상태 불러오기 등등

- redux-thunk로 못하는 작업
    1. 비동기 작업을 할 때 기존 요청을 취소 처리 
    2. 특정 액션이 발생했을 때 이에 따라 다른 액션이 디스패치되게끔 하거나, 자바스크립트 코드를 실행
    3. 웹소켓을 사용하는 경우 Channel 이라는 기능을 사용하여 더욱 효율적으로 코드를 관리
    4. API 요청이 실패했을 때 재요청하는 작업

## Generator 문법

- 함수를 작성 할 때 함수를 특정 구간에 멈춰 놓기.
- 원할 때 다시 돌아가거나 결과값을 여러번 반환 할 수 있음

```jsx
function* generatorFunction() {
    console.log('안녕하세요?');
    yield 1;
    console.log('제너레이터 함수');
    yield 2;
    console.log('function*');
    yield 3;
    return 4;
}

const generator = generatorFunction();

generator.next() // 제너레이터 함수 실행
```

```jsx
function* sumGenerator() {
    console.log('sumGenerator이 시작됐습니다.');
    let a = yield;
    console.log('a값을 받았습니다.');
    let b = yield;
    console.log('b값을 받았습니다.');
    yield a + b;
}

const generator = sumGenerator();

generator.next() // 제너레이터 함수 실행
generator.next(1);
generator.next(2);
```

- 액션을 모니터링하고 특정 액션 발생시 원하는 코드 실행

```jsx
function* watchGenerator() {
    console.log('모니터링 시작!');
    while(true) {
        const action = yield;
        if (action.type === 'HELLO') {
            console.log('안녕하세요?');
        }
        if (action.type === 'BYE') {
            console.log('안녕히가세요.');
        }
    }
}

const generator = watchGenerator();

generator.next();
generator.next({type:'HELLO'});
```

## redux-saga/effects

### 1. put

- 새로운 액션 디스패치 가능

```jsx
yield put(액션생성함수());
```

### 2. takeEvery

- 특정 액션 타입에 대하여 디스패치되는 모든 액션들을 처리

```jsx
yield takeEvery(INCREASE_ASYNC, increaseSaga);
```

### 3. takeLatest

- 특정 액션 타입에 대하여 디스패치된 가장 마지막 액션만 처리
- 기존에 하던 작업을 무시 처리하고 새로운 작업을 시작

```jsx
yield takeLatest(DECREASE_ASYNC, decreaseSaga);
```

## root saga

```jsx
// module 폴더 파일
export function* postsSaga() {
  yield takeEvery(GET_POSTS, getPostsSaga);
  yield takeEvery(GET_POST, getPostSaga);
}

// module/index.js
const rootReducer = combineReducers({ counter, posts });

export function* rootSaga() {
	// all 은 배열 안의 여러 사가를 동시에 실행시켜줍니다.
  yield all([counterSaga(), postsSaga()]); 
}
```

## index.js

```jsx
const sagaMiddleware = createSagaMiddleware(); // 사가 미들웨어를 만듭니다.

const store = createStore(
  rootReducer,
  // logger 를 사용하는 경우, logger가 가장 마지막에 와야합니다.
  composeWithDevTools(
    applyMiddleware(
      sagaMiddleware, // 사가 미들웨어를 적용하고
      logger
    )
  )
); // 여러개의 미들웨어를 적용 할 수 있습니다.

sagaMiddleware.run(rootSaga); // 루트 사가를 실행해줍니다.
// 주의: 스토어 생성이 된 다음에 위 코드를 실행해야합니다.

ReactDOM.render(
  <Router>
    <Provider store={store}>
      <App />
    </Provider>
  </Router>,
  document.getElementById('root')
);
```