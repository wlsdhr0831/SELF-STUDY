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

## 완전 검색

- brute-force, generate-and-test, exhaustive-search
- **모든 경우의 수**를 나열 후 확인함으로서 최종 해법 도출
- 상대적으로 **빠른 시간에 알고리즘 설계** 가능
- **경우의 수**가 상대적으로 **적을 때** 유용
- **순열, 조합, 부분집합**과 같은 조합적 문제들 (Combinational Problems) 과 연관

# 순열 (Permutation)

: n개 중에 r개 뽑아 줄 세우기 (중복 여부에 따라 다름)

```java
**nPr = n * (n-1) * (n-2) * ... * (n-r+1)**

**nPn = n!**
```

ex) TSP ( 외판원 문제 )

: 여러 도시들이 있고 한 도시에서 다른 도시로 이동하는 비용이 모두 주어졌을 때, 

모든 도시들을 단 한 번만 방문하고 원래 시작점으로 돌아오는 **최소 비용의 이동 순서**를 구하는 것

```java
permutation(0);

void permutation(int cnt){
	if(cnt == r){
		// r개 고르고 할 일 수행
		return ;
	}

	for(int i = 0; i < N; i++){
		// 중복이 없으면 체크하고 중복이 허용되면 생략
		if(selected[i]) continue;

		selected[i] = true;
		permutation(cnt+1);
		selected[i] = false;
	}
}
```

- Next Permutation 사용 시 초기 값

```java
for(int i = 0; i < N; i++)
	array[i] = i;
```

# 조합 (Combination)

: n개 중에 r개 순서 없이 뽑기 (중복 여부에 따라 다름)

```java
**nCr = n! / (n-r)! * r! = n-1Cr-1 + n-1Cr

nC0 = nCn = 1**
```

: 숫자 자체로 배열 만들면 시간과 메모리 증가

⇒ index 순열을 만드는 것이 더 나음

```java
combination(0,0);

void combination(int idx, int cnt){
	if(cnt == r){
		// r개 고르고 할 일 수행
		return ;
	}

	for(int i = idx; i < N; i++){
		selected[i] = true;
		// 중복이 없으면 i+1, 중복이 있으면 i
		permutation(i, cnt+1);
		selected[i] = false;
	}
}
```

- Next Permutation 사용 시 초기 값

```java
int cnt = 0;
while(++cnt <= R){
	array[N-cnt] = 1;
}
```

# 부분 집합 (Subset)

: 집합에 포함된 원소들 선택하기

: n개 원소

⇒ **2^n개의 부분 집합** ( 포함 / 비포함 )

⇒ 0~n까지의 원소 갯수! **선택된 원소들의 갯수가 가변적**

ex) knapsack (배낭 짐싸기)

: 한 여행가가 가지고 가는 배낭에 담을 수 있는 무게의 최댓값이 정해져 있고, 일정 가치와 무게가 있는 짐들을 배낭에 넣을 때, 가치의 합이 최대가 되도록 **짐을 고르는 방법**을 찾는 문제

```java
subset(0,0);

void subset(int idx, int v){
	if(idx == N){
		// 부분 집합이 정해졌을 때 할 일 수행
		return ;
	}

	for(int i = idx; i < N; i++){
		if(selected[i]) continue;

		selected[i] = true;
		permutation(i+1, v + value[i]);
		selected[i] = false;
		permutation(i+1, v);
	}
}
```