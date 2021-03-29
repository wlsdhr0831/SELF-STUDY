# Axios란?

- 브라우저, Node.js를 위한 Promise API를 활용하는 HTTP 비동기 통신 라이브러리

# **기능**

- 브라우저 환경: **[XMLHttpRequests](https://developer.mozilla.org/ko/docs/Web/API/XMLHttpRequest)** 요청 생성
- Node.js 환경: **[http](https://nodejs.org/api/http.html)** 요청 생성
- **[Promise](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Promise)** API 지원
- 요청/응답 차단(Intercept)
- 요청/응답 데이터 변환
- 취소 요청
- JSON 데이터 자동 변환
- **[사이트 간 요청 위조(XSRF)](https://goo.gl/HHzpwP)** 보호를 위한 클라이언트 사이드 지원

# 설치

```json
- npm 설치
npm install axios
yarn add axios

- cdn 설치
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
```

# 요청 작성 방법

## configuration 설정

```jsx
// 원격 이미지 GET 요청
axios({
  method:'get',
  url:'http://bit.ly/2mTM3nY',
  responseType:'stream'
	})
	.then(function (response) {
	  response.data.pipe(fs.createWriteStream('ada_lovelace.jpg'))
});

// GET 요청 전송 (기본 메서드)
axios('/user/12345');
```

```jsx
// POST 요청 전송
axios({
  method: 'post',
  url: '/user/12345',
  data: {
    firstName: 'Fred',
    lastName: 'Flintstone'
  }
});
```

## HTTP 메서드 별칭

```jsx
axios.get(url[, config])            // GET
axios.post(url[, data[, config]])   // POST
axios.put(url[, data[, config]])    // PUT
axios.patch(url[, data[, config]])  // PATCH
axios.delete(url[, config])         // DELETE

axios.request(config)
axios.head(url[, config])
axios.options(url[, config])
```

⇒ 별칭 사용시 설정에서 url, method, data 속성을 지정할 필요가 없다.

## GET 요청

```jsx
axios.get('/user?ID=12345')

axios.get('/user', {
    params: {
      ID: 12345
    }
  })
  .then(function (response) {
    // 요청 성공 시
  })
  .catch(function (error) {
    // 요청 실패 시
  })
  .then(function () {
    // 항상 실행
  });
```

```jsx
async function getUser() {
  try {
    const response = await axios.get('/user?ID=12345');
    // 요청 성공 시
  } catch (error) {
    // 요청 실패 시
  }
}
```

⇒ ES8에 추가 된 새로운 방법! (오래된 브라우저는 지원하지 않음)

## POST 요청

```jsx
axios.post('/user', 
	{
    firstName: 'Fred',
    lastName: 'Flintstone'
  })
  .then(function (response) {
    // 요청 성공 시
  })
  .catch(function (error) {
    // 요청 실패 시
  });
```

## 동시 요청 처리를 위한 헬퍼 함수 (멀티 요청)

```jsx
function getUserAccount() {
  return axios.get('/user/12345');
}

function getUserPermissions() {
  return axios.get('/user/12345/permissions');
}

axios.all([getUserAccount(), getUserPermissions()])
  .then(axios.spread(function (acct, perms) {
    // Both requests are now complete
  }));
```

## 인스턴스 생성

⇒ **.create() 함수**를 이용하여 사용자 정의 구성을 사용하는 axios 인스턴스 생성

```jsx
const instance = axios.create({
  baseURL: 'https://some-domain.com/api/',
  headers: { 'X-Custom-Header': 'foobar' },
  timeout: 1000,
});
```

## 구성 옵션

```jsx
{
  // `url`은 요청에 사용될 서버 URL입니다.
  url: '/user',

  // `method`는 요청을 할 때 사용될 메소드 이름입니다.
  method: 'get', // 기본

  // `url` 속성 값이 절대 URL이 아니라면, `url` 앞에 `baseURL`이 붙습니다.
  // axios 인스턴스가 상대 URL을 해당 인스턴스의 메소드에 전달하도록
  // `baseURL`을 설정하는 것이 편리 할 수 있습니다.
  baseURL: 'https://some-domain.com/api/',

  // `transformRequest`는 서버에 보내기 전에 요청 데이터를 변경할 수 있습니다.
  // 요청 메소드 'PUT', 'POST' 및 'PATCH' 에만 적용 가능합니다.
  // 배열의 마지막 함수는 버퍼(buffer)의 문자열이나 인스턴스를 반환해야 합니다.
  // ArrayBuffer, FormData 또는 Stream 헤더 객체를 수정할 수 있습니다.
  transformRequest: [function (data, headers) {
    // 데이터 변환 수행 후, 반환
    // ...
    return data;
  }],

  // `transformResponse`는 응답할 데이터에 대한 변경을 전달해
  // then/catch에 전달하도록 허용합니다.
  transformResponse: [function (data) {
    // 데이터 변환 수행 후, 반환
    // ...
    return data;
  }],

  // `headers`는 서버에 전송 될 사용자 정의 헤더 입니다.
  headers: { 'X-Requested-With': 'XMLHttpRequest' },

  // `params`는 요청과 함께 전송 될 URL 매개 변수입니다.
  // 일반 객체 이거나 URLSearchParams 객체여야 합니다.
  params: {
    ID: 12345
  },

  // `paramsSerializer`는`params`를 직렬화(serializing) 하는 옵션 함수입니다.
  // (예: https://www.npmjs.com/package/qs, http://api.jquery.com/jquery.param/)
  paramsSerializer: function (params) {
    return Qs.stringify(params, {arrayFormat: 'brackets'})
  },

  // `data`는 요청 본문(request body)으로 전송할 데이터입니다.
  // 'PUT', 'POST' 및 'PATCH' 요청 메소드에만 적용 가능합니다.
  // 'transformRequest`가 설정되지 않은 경우 다음 유형 중 하나여야 합니다.
  // - [ string, plain object, ArrayBuffer, ArrayBufferView, URLSearchParams ]
  // - 브라우저 전용: FormData, File, Blob
  // - Node.js 전용: Stream, Buffer
  data: {
    firstName: 'Fred'
  },

  // `timeout`은 요청이 타임 아웃되는 밀리 초(ms)를 설정합니다.
  // 요청이`timeout` 설정 시간보다 지연될 경우, 요청은 중단됩니다.
  timeout: 1000, // 기본 값: `0` (타임아웃 없음)

  // `withCredentials`는 자격 증명(credentials)을 사용하여
  // 크로스 사이트 접근 제어(cross-site Access-Control) 요청이 필요한 경우 설정합니다.
  withCredentials: false, // 기본 값

  // `adapter`는 테스트를 보다 쉽게 해주는 커스텀 핸들링 요청을 허용합니다.
  // 유효한 응답(Promise)을 반환해야 합니다. (lib/adapters/README.md 참고).
  adapter: function (config) {
    // ...
  },

  // `auth`는 HTTP 기본 인증(auth)이 사용되며, 자격 증명(credentials)을 제공함을 나타냅니다.
  // 기존의 `Authorization` 커스텀 헤더를 덮어쓰는 `Authorization` 헤더(header)를 설정합니다.
  auth: {
    username: 'janedoe',
    password: 's00pers3cret'
  },

  // `responseType`은 서버에서 응답할 데이터 타입을 설정합니다.
  // [ 'arraybuffer', 'blob', 'document', 'json', 'text', 'stream' ]
  responseType: 'json', // 기본 값

  // `responseEncoding`은 응답 디코딩에 사용할 인코딩을 나타냅니다.
  // [주의!] 클라이언트 사이드 요청 또는 `responseType`이 'stream'인 경우는 무시합니다.
  responseEncoding: 'utf8', // 기본 값

  // `xsrfCookieName`은 xsrf 토큰(token)에 대한 값으로 사용할 쿠키 이름입니다.
  xsrfCookieName: 'XSRF-TOKEN', // 기본 값

  // `xsrfHeaderName`은 xsrf 토큰 값을 운반하는 HTTP 헤더 이름입니다.
  xsrfHeaderName: 'X-XSRF-TOKEN', // 기본 값

  // `onUploadProgress`는 업로드 프로그래스 이벤트를 처리합니다.
  onUploadProgress: function (progressEvent) {
    // 네이티브 프로그래스 이벤트(Native Progress Event) 처리 코드
    // ...
  },

  // `onDownloadProgress`는 다운로드 프로그래스 이벤트를 처리합니다.
  onDownloadProgress: function (progressEvent) {
    // 네이티브 프로그래스 이벤트(Native Progress Event) 처리 코드
    // ...
  },

  // `maxContentLength`는 HTTP 응답 콘텐츠의 최대 크기를 바이트(Bytes) 단위로 설정합니다.
  maxContentLength: 2000,

 // `validateStatus`는 주어진 HTTP 응답 상태 코드에 대한 약속을 해결할지 거절 할지를 정의합니다.
 // `validateStatus`가`true`를 반환하면 (또는`null`,`undefined`) promise를 resolve 합니다.
 // 그렇지 않으면 promise가 reject 됩니다.
  validateStatus: function (status) {
    return status >= 200 && status < 300; // 기본 값
  },

  // `maxRedirects`는 Node.js에서 리디렉션 가능한 최대 갯수를 정의합니다.
  // 0으로 설정하면 리디렉션이 수행되지 않습니다.
  maxRedirects: 5, // 기본 값

  // `socketPath`는 Node.js에서 사용될 UNIX 소켓을 정의합니다.
  // 예: '/var/run/docker.sock'을 사용하여 docker 데몬에 요청을 보냅니다.
  // `socketPath` 또는`proxy`만이 지정 될 수 있습니다.
  // 둘 다 지정되면`socketPath`가 사용됩니다.
  socketPath: null, // 기본 값

  // `httpAgent`와`httpsAgent`는 각각 Node.js에서 http와 https 요청을 수행 할 때
  // 사용할 커스텀 에이전트를 정의합니다. 이것은 기본적으로 활성화되지 않은 `keepAlive`와 같은
  // 옵션을 추가 할 수 있게 합니다.
  httpAgent: new http.Agent({ keepAlive: true }),
  httpsAgent: new https.Agent({ keepAlive: true }),

  // 'proxy'는 프록시 서버의 호스트 이름과 포트를 정의합니다.
  // 기존의 `http_proxy` 및 `https_proxy` 환경 변수를 사용하여 프록시를 정의 할 수도 있습니다.
  // 프록시 설정에 환경 변수를 사용하고 있다면 `no_proxy` 환경 변수를 쉼표로 구분 된 도메인 목록으로
  // 정의하여 프록시 할 필요가 없습니다.
  // 환경 변수를 무시하고 프록시를 사용하지 않으려면 `false`를 설정합니다.
  // `auth`는 HTTP 기본 인증(Basic Auth)를 사용하여 프록시에 연결하고 자격 증명을 제공해야 함을 나타냅니다.
  // 기존의 `Proxy-Authorization` 커스텀 헤더를 덮어쓰는 `Proxy-Authorization` 헤더(header)를 설정합니다.
  proxy: {
    host: '127.0.0.1',
    port: 9000,
    auth: {
      username: 'mikeymike',
      password: 'rapunz3l'
    }
  },

  // `cancelToken`은 요청을 취소하는 데 사용할 수 있는 취소 토큰을 지정합니다.
  // (자세한 내용은 해제(Cancellation) 섹션 참조).
  cancelToken: new CancelToken(function (cancel) {
    // ...
  })

}
```

### config 기본 설정

1. **글로벌 axios 기본 설정**

```jsx
axios.defaults.baseURL = 'https://api.example.com';
axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
```

2. **사용자 정의 인스턴스 기본 설정**

```jsx
// axios 인스턴스를 만들 때 구성 기본 값 설정
const instance = axios.create({
  baseURL: 'https://api.example.com'
});

// 인스턴스가 생성 된 후 기본값 변경
instance.defaults.headers.common['Authorization'] = AUTH_TOKEN;
```

- **구성 우선 순위 규칙**

```jsx
// 라이브러리에서 제공하는 config 기본 값을 사용하여 인스턴스를 만들면
// 이 시점에서 라이브러리의 기본 값인 timeout 구성은 '0' 입니다.
const instance = axios.create();

// 라이브러리의 timeout 기본 값을 2.5초로 재 정의하여
// 인스턴스의 모든 요청은 2.5초 간만 대기 후 타임아웃 처리합니다.
instance.defaults.timeout = 2500;

// 최종적으로 인스턴스에 설정된 timeout 구성 값 5000으로 덮어씁니다.
instance.get('/longRequest', {
  timeout: 5000
});
```

⇒ **인스턴스 호출 메서드 옵션 > 인스턴스.defaults 설정 옵션 > 인스턴스.create()에 설정된 옵션**

# 응답 스키마

```jsx
{
  // `data`는 서버가 제공한 응답(데이터)입니다.
  data: {},

  // `status`는 서버 응답의 HTTP 상태 코드입니다.
  status: 200,

  // `statusText`는 서버 응답으로 부터의 HTTP 상태 메시지입니다.
  statusText: 'OK',

  // `headers` 서버가 응답 한 헤더는 모든 헤더 이름이 소문자로 제공됩니다.
  headers: {},

  // `config`는 요청에 대해 `axios`에 설정된 구성(config)입니다.
  config: {},

  // `request`는 응답을 생성한 요청입니다.
  // 브라우저: XMLHttpRequest 인스턴스
  // Node.js: ClientRequest 인스턴스(리디렉션)
  request: {}
}
```

# 인터셉터

## 1. 인터셉터 추가

```jsx
axios.interceptors.request.use(
  function (config) {
    // 요청을 보내기 전에 수행할 일
    // ...
    return config;
  },
  function (error) {
    // 오류 요청을 보내기전 수행할 일
    // ...
    return Promise.reject(error);
  });

// 응답 인터셉터 추가
axios.interceptors.response.use(
  function (response) {
    // 응답 데이터를 가공
    // ...
    return response;
  },
  function (error) {
    // 오류 응답을 처리
    // ...
    return Promise.reject(error);
  });
```

## 2. 인터셉터 제거

```jsx
const myInterceptor = axios.interceptors.request.use(function () { /*...*/ });
axios.interceptors.request.eject(myInterceptor);
```

## 3. axios 인스턴스에 인터셉트 추가

```jsx
const instance = axios.create();
instance.interceptors.request.use(function () { /*...*/ });
```

# 오류 처리

```jsx
axios.get('/user/12345')
  .catch(function (error) {
    if (error.response) {
      // 요청이 이루어졌으며 서버가 2xx의 범위를 벗어나는 상태 코드로 응답했습니다.
      console.log(error.response.data);
      console.log(error.response.status);
      console.log(error.response.headers);
    }
    else if (error.request) {
      // 요청이 이루어 졌으나 응답을 받지 못했습니다.
      // `error.request`는 브라우저의 XMLHttpRequest 인스턴스 또는
      // Node.js의 http.ClientRequest 인스턴스입니다.
      console.log(error.request);
    }
    else {
      // 오류를 발생시킨 요청을 설정하는 중에 문제가 발생했습니다.
      console.log('Error', error.message);
    }
    console.log(error.config);
  });

```

- validateStatus를 사용하여 **사용자 정의 HTTP 상태 코드 오류 범위를 정의**할 수 있습니다.

```jsx
axios.get('/user/12345', {
  validateStatus: function (status) {
    // 상태 코드가 500 이상일 경우 거부. 나머지(500보다 작은)는 허용.
    return status < 500;
  }
})
```

# 해체 (Cancellation)

- 취소 토큰을 사용하여 요청 취소

```jsx
const CancelToken = axios.CancelToken;
const source = CancelToken.source(); // 취소 토큰 생성

axios.get('/user/12345', {
  cancelToken: source.token
}).catch(function (thrown) {
  if (axios.isCancel(thrown)) {
    console.log('Request canceled', thrown.message);
  } else {
    // 오류 처리
  }
});

axios.post('/user/12345', {
  name: 'new name'
}, {
  cancelToken: source.token
})

// 요청 취소 (message 매개 변수는 선택 사항)
source.cancel('Operation canceled by the user.');
```

- executor 함수를 cancleToken 생성자에 전달하여 취소 토큰 생성

```jsx
const CancelToken = axios.CancelToken;
let cancel;

axios.get('/user/12345', {
  cancelToken: new CancelToken(function executor(c) {
    // excutor 함수는 cancel 함수를 매개 변수로 받습니다.
    cancel = c;
  })
});

// 요청 취소
cancel();
```

# application/x-www-form-urlencoded 대신 데이터

## 1. URLSearchParams API 사용

```jsx
const params = new URLSearchParams();
params.append('param1', 'value1');
params.append('param2', 'value2');
axios.post('/foo', params);
```

## 2. qs 라이브러리 사용 → 데이터 인코딩

```jsx
const qs = require('qs');
axios.post('/foo', qs.stringify({ 'bar': 123 }));
```

## 3. ES6+ 사용

```jsx
import qs from 'qs';
const data = { 'bar': 123 };
const options = {
  method: 'POST',
  headers: { 'content-type': 'application/x-www-form-urlencoded' },
  data: qs.stringify(data),
  url,
};
axios(options);
```

## 4. Node.js

### 1) querystring 모듈 사용

```jsx
const querystring = require('querystring');
axios.post('http://something.com/', querystring.stringify({ foo: 'bar' }));
```

### 2) qs 라이브러리 사용

```jsx
const qs = require('qs');
axios.post('http://something.com/', qs.stringify({ foo: 'bar' }));
```

# 이미지, 영상 전송

```jsx
data: e.target.files[0],

header: {
	'Content-Type': 'multipart/form-data',
}
```