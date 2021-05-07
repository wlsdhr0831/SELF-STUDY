// 배열 자르기 (시작 포함, 끝점 미포함)
let newArray = array.slice(시작점, 끝점)

// 배열 정렬 ( 앞 - 뒤 : 1 이면 오름차순 )
let newArray = array.sort(() => { 실행문 });

// 요일 검색
let day = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'][new Date(year, month - 1, date).getDay()];

// 조건 거르기 (리턴 true 인 item 남김)
let newArray = array.filter(( item, idx ) => { 실행문 });

// 두 수 바꾸기
[a, b] = [b, a];

// 사전순 정렬시 a가 앞이면 -, 동일하면 0, 뒤면 +
a.localeCompare(b);

// 배열의 시작부터 끝까지 a로 채움
let newArray = array.fill(a);

// 배열의 모든 요소를 하나씩 돌면서 공통적인 행위
let newArray = array.map(x => x*2);

// 배열의 각 요소에 대해서 함수 실행 
// acc : 그 전 요소 함수에서 리턴되는 값
// cur :  현재 요소값
// intialValue: 초기 값 
let result = array.reduce(( acc, cur ) => { acc + cur}, iniitalValue);

// 문자열을 특정 문자열 기준으로 나누고 배열 만들기
let newArray = string.split('');

// 문자열에 특정 문자열 포함 여부 확인
string.includes(substring);

// 대소문자 구분 없이 string에서 a를 b로 치환
let newString = string.replace('/a/gi',b);

// 몰라 정규식 쓰라는데 모르겟음
let newArray = array.match();

// 문자열 배열을 하나의 문자열로 합치기
let string = array.join(' ');

// 배열에서 해당 요소 인덱스 찾기
let idx = array.indexOf('요소');

// 반복횟수만큼 반복으로 적힌 문자열 반환
let string = 'substring'.repeat(반복횟수);

// 아스키 코드 숫자 변환
let code = char.charCodeAt();
let char = string.fromCharCode();

// 대소문자 만들기
string.toLoserCase();
string.toUpperCase();

// 배열 뒤집기
let newArray = array.reverse();

// math함수
let min = Math.min(...arr);
let max = Math.max(...arr);

//제곱
let a = b ** 2;