## 25년 09월 10일 2주차 수업(기본 환경설정)

## 25년 09월 16일 기본 환경설정 오류 수정(22시 25분)

## 25년 09월 16일 2주차 과제 해결(23시 33분)

스스로 생각하여 응용1(한줄 표현), 응용2(실무자 관점에서 반복문 표현) 추가

## 2주차 핵심 요약

1. MVC 패턴: 스프링 부트의 기본 동작 방식
   Controller: URL 요청을 받아 처리 방법을 결정 (@GetMapping)
   Model: Controller가 View로 전달할 데이터를 담는 상자 (model.addAttribute)
   View: Model의 데이터로 사용자에게 보여줄 최종 화면 (HTML + Thymeleaf)

2. 데이터 전달 흐름
   Controller: model.addAttribute("key", 데이터)로 Model에 데이터를 담는다.
   View (HTML): Thymeleaf 문법 ${key}를 사용해 Model의 데이터를 꺼내 쓴다.

3. 여러 데이터 처리 Best Practice
   Controller: 여러 데이터를 List로 묶어 Model에 한 번에 담는다.
   View (HTML): th:each 반복문을 사용해 List의 모든 데이터를 자동으로 출력한다.

## 25년 09월 17일 3주차 수업(포트폴리오)

## 25년 09월 22일 3주차 과제 해결(22시 52분)

## 25년 09월 17일 4주차 수업(DB연동)
