## 25년 09월 10일 기본 환경설정

## 25년 09월 16일 기본 환경설정 오류 수정(10시 25분)

## 25년 09월 16일 2주차 과제 해결(11시 33분)
스스로 생각하여 응용1(한줄 표현), 응용2(실무자 관점에서 반복문 표현) 추가

## 2주차 내용 정리(설정 제외)

1. 스프링 부트의 기본 동작 원리: MVC 패턴의 이해
Controller (조정자): 사용자의 URL 요청(localhost:8080/hello 등)을 가장 먼저 받는 객체, 어떤 일을 할지 결정하고 지시함
View (화면): 사용자에게 보여질 최종 결과물
Model (데이터 상자): Controller가 View에게 전달할 데이터를 담는 상자

2. 핵심 프로그래밍 3요소
   
2-1) View 작성: hello.html 만들기(html 기본 내용은 자바웹(1)에서 배운 부분)
<html xmlns:th="http://www.thymeleaf.org">
 -> Thymeleaf 문법으로, Model에 담긴 데이터 중 이름이 "data"인 것을 찾아 그 값을 이 태그의 텍스트로 출력하라는 의미

2-2) Controller 작성: 요청 경로와 View 연결하기
@Controller: 이 클래스가 웹 요청을 처리하는 역할을 한다고 스프링 전달
@GetMapping("/경로"): 특정 URL 경로로 들어오는 GET 요청을 아래 메서드와 연결함
return "파일이름": templates 폴더 안에 있는 해당 이름의 .html 파일을 찾아 사용자에게 보여주라는 지시

2-3) Model을 이용한 데이터 전달(해시테이블로 생각하면 편함)
Controller 메서드의 파라미터로 Model model을 추가합니다.
model.addAttribute("Key", "Value"); 형태로 데이터를 담습니다.
"Key": HTML에서 데이터를 꺼낼 때 사용할 키
"Value": 실제로 전달할 값

3. 연습문제 응용 및 심화: 여러 데이터 처리하기
   
3-1) 기본 풀이
Controller: addAttribute를 여러 번 사용하되, 각 데이터마다 고유한 Key를 부여
View: Controller에서 지정한 각각의 Key를 사용하여 p 태그를 여러 개 만듬

3-2) 응용 풀이 1
Controller: 기본 풀이와 동일.
View: th:utext와 <br> 태그를 이용해 한 줄로 합쳐서 표현
 -> 문제점으로는 utext을 사용하여 보안 취약과 한 줄이지만 길어 가독성이 떨어지고 데이터와 화면 로직이 섞여 있음
   좋은 방식은 아닌듯함

3-3) 응용 풀이 2
Controller: 전달할 데이터들을 List(목록)로 묶어서, List 자체를 Model에 딱 한 번 넣음
View: th:each 반복문을 사용하여 List의 아이템을 하나씩 자동으로 출력

4. 2주차 주요 문법
   
4-1) Java Controller 문법
@Controller    @GetMapping("/경로")	                  public String 메서드명()
Model model    model.addAttribute("Key", "Value")	    import
List<String>	

4-2) HTML View 문법
xmlns:th="..."         th:text="${key}"
th:utext="${key}"      th:each="변수명 : ${컬렉션}"	













