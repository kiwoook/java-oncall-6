## 기능 구현

- 비상 근무를 배정할 월과 시작 요일 입력 검증하는 기능
- 평일 비상 근무 순번대로 사원 닉네임 입력
- 휴일 비상 근무 순번대로 사원 닉네임 입력
  - 순번에는 이름이 중복되면 안된다.
  - 닉네임은 5자 이하로만 한다.
  - 휴일 평일 비상 근무자는 각각 최소 5명 미만이면 안된다.
  - 전체 인원이 35명을 초과하지 않는다.
- 해당 월에 따라 해당 월의 일수만큼 처리하는 근무자를 배정하는 기능
- 시작 일과 시작 요일에 따라 평일, 주말인지 확인하는 기능
- 평일, 주말에 따라 근무자를 배정하는 기능
- 평일과 휴일이 변경되어서 다음 근무자를 배정할 때 이전 근무자와 이름이 연속적으로 나오면 안된다.
- 만약 연속될 경우 다음 순번을 먼저  배정하고 연속된 순번을 이전에 넣어서 다음 순번에 배정하도록 한다.
- 월과 일을 입력받고 법정 공휴일인지 확인하는 기능
- 순번 이름 목록이 완성되면 월, 일, 요일, 이름을 오름차순으로 출력한다.
  - 이때 평일이면서 법정 공휴일인 경우에는 (휴일)이라고 표시를 한다.