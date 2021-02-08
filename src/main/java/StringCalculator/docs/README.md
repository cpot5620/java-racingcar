## TDD - 문자열 덧셈 계산기✖️➕➖➗

문자열을 기본 구분자 또는 주어진 구분자로 스플릿하여 덧셈을 하는 계산기이다.

### 목표

- 단위 테스트를 통한 테스트 코드 작성을 연습한다.
- 테스트 코드로 부터 프로덕션 코드를 생성해 냄으로써 기본적인 TDD의 싸이클을 연습한다.

### 기능 목록

- 커스텀 구분자를 만들 수 있다.
    - 구현: \\와\n 사이에 커스텀 구분자를 체크 한다.
    - 예외: 커스텀 구분자 등록하기 위한 \\과 \n의 위치가 문자열 앞이 아닌 경우
        - IllegalFormatException
    - 예외: 구분자로 숫자는 들어 올 수 없다.
        - IllegalArgumentException

- 구분자에 따라 문자열을 분리한다.
    - 구현: 빈 문자열 또는 NULL 값이 들어올 경우 0을 반환해야 한다.
    - 커스텀 구분자가 설정되어 있는 경우.
        - 구현: 커스텀 구분자를 기본 구분자로 치환한 뒤 스플릿한다.   
    - 커스텀 구분자가 설정되어 있지 않은 경우
        - 구현: 기본 구분자(쉼표, 콜론)으로 스플릿 한다.
    - 구현: 숫자 하나가 들어올 경우 해당 값을 리턴한다.

- 분리 된 문자열을 숫자로 변환하여 덧셈 한다.
    - 예외: 분리 된 문자열 중 숫자가 아닌 것이 있는 지 체크 한다.
        - IllegalArgumentException
    - 예외: 분리 된 문자열 중 음수가 있는 지 체크 한다.
        - IllegalArgumentException