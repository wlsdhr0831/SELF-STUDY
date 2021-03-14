## 반복

- 수행하는 작업이 **완료될 때까지 계속 반복**

## 재귀

- 주어진 문제의 해를 구하기 위해 **동일하면서 더 작은 문제의 해**를 이용

    ⇒ 작은 문제들의 결과를 결합

- **기본파트 ( 종료 조건 ) + 유도파트 ( 다음 함수 호출 )**

    ⇒ stack 메모리 사용으로 재귀가 많아지면 메모리 및 속도 성능 저하

- 중복호출은 **memoization**으로 해결

## Next Permutation

- 헌 순열에서 사전순으로 다음 순열 생성

```java
while(nextPermutation()){
	// 순열 생성 후 할 일 진행
}

nextPermutation(){
	int i = N-1;
	while(i > 0 && array[i-1] >= array[i-]) i--;
	if(i <= 0) return false;

	int j = N-1;
	while(array[i-1] > array[j]) j--;
	swap(i-1, j);

	j = N-1;
	while(i < j){
		swap(i++, j--);
	}
	return true;
}
```