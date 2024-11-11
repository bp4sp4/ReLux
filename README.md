## RELUX - 작업 완료

<h1>작업환경 : macOS, 작업툴 : VSCode, STS</h1>
<h1>배포환경 : Amazon Web Services</h1>

## 작업기간 - 24/09/27 ~ 24/11/03 - 약1개월

<div align=center><h1>📚 STACKS</h1></div>
<div align=center> 
  <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> 
  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> 
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> 
  <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white">
  <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
  <br>
  <img src="https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white">
  <img src="https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
  <img src="https://img.shields.io/badge/fontawesome-339AF0?style=for-the-badge&logo=fontawesome&logoColor=white">
  <br>
</div>
(마우스 우클릭 통해 새창으로 열어 봐주시면 편하게 보실수 있습니다.)
<h2>ReLux Demo - <a target="_blank" href="http://43.202.49.54:8080/main/list-view" >ReLux</a> <br>
Relux All Figma - <a target="_blank" href="https://www.figma.com/design/OlX9bcW7SySE93HnEr58QZ/ReLux?node-id=0-1&node-type=canvas&t=9IEPqH6qpLlMf6kn-0">Figma</a></h2>

- 사용자 계정 : 아이디 : user001 비밀번호 : user001!
- 어드민 계정 : 아이디 : admin 비밀번호 : admin1234 (배너등록 테스트시 사용가능)

<h2>📌 프로젝트 진행이유</h2>

- 백엔드 기술향상 웹의 흐름 이해도 향상을 위해 시작
- 웹개발자 정도면 백엔드가 작동되는 홈페이지를 만들기 위해 시작
- 아마존 웹서비스를 통해 배포하기
- 초기홈페이지 디자인
  
  <img width="585" alt="image" src="https://github.com/user-attachments/assets/e32fa8a2-ae02-4e41-a49e-ac289c5204ff">

- 이후 만들어진 홈페이지 디자인

  <img width="675" alt="image" src="https://github.com/user-attachments/assets/9aa29f1e-5765-4464-89fd-a62f78cee670">

<h2>🌠 트러블 슈팅 </h2>

<h4>문제 상황</h4>

- 프로젝트에서 특정 연령대와 브랜드에 맞는 브랜드 리스트를 필터링하는 getFilteredBrands 메서드를 작성하고 있었으나, 일부 필터링 조합에서 예상한 결과가 출력되지 않는 문제가 발생했다. 특히, 사용자가 "나이: 전체, 브랜드: 특정 브랜드"와 같이 특정 브랜드를 선택하고 나이를 "전체"로 지정한 경우, 올바른 리스트가 출력되지 않는 이슈가 있었다

<h4> 문제 원인</h4>

- 문제의 원인은 특정 조건에서의 필터링 로직이 제대로 작동하지 않는 데 있었다. age가 "전체"일 때 brand가 특정 값으로 주어지면 조건이 복잡해지면서 일부 케이스가 누락되거나 잘못된 필터링 조건으로 넘어가는 경우가 있었다.

<h4> 해결 방법 </h4>

- Null 및 빈 문자열 체크 추가: age 및 brand 파라미터에 대해 빈 문자열과 null 체크를 추가하여, 불필요한 조건을 제거했고, Console.log를 통한 데이터가 실제로 가져오는지 확인 한 후 필터링 로직을 리팩토링한 후 코드 가독성을 높이기 위해 일부 조건을 메서드로 분리하였고, switch 문을 정리하여 중복 코드를 줄였다.

<h2>❗ 어려웠던점</h2>

- Summernote는 프론트엔드에서 HTML 기반의 내용을 작성하고 이를 서버로 전송하는 방식이어서 이미지 업로드나 텍스트 저장이 다소 복잡할 수 있습니다. 특히, 이미지 업로드와 같은 기능을 구현할 때 몇 가지 어려움이 있었다.
- 이미지 업로드 요청을 처리하는 API를 백엔드에 작성하였고, Summernote의 callbacks 옵션을 활용해 사용자가 이미지를 추가할 때마다 서버로 전송하도록 설정했고, 이 API는 이미지를 서버의 특정 경로에 저장한 후, 저장된 이미지의 경로를 반환하여 Summernote의 src 속성에 해당 경로를 설정하게 했다.

<h2>📎  아쉬운점</h2>

- 아쉬운점이 있다면 react와 스프링 연결하는 법을 못해 html,css로 진행된게 아쉬움이 많이 남는다.
- 비주얼 업데이트를 반복했음에도 생각했던 만큼의 완성도에 도달하지 못해 아쉬움이 남습니다. 특히 반응형 디자인이나 사용자 인터페이스의 세부 요소들에서 부족함을 느꼈다. 디자인에 있어 보다 많은 참고 자료와 트렌드를 공부하고, CSS와 디자인 라이브러리 활용도를 높여야겠다고 느꼈다.
- 프로젝트 진행 중 기능을 기획했던 것보다 일정이 지연되는 경험을 했다. 초기 계획 수립 시 모든 기능을 충분히 고려하지 못한 점도 있었고, 각 기능에 소요되는 시간을 과소평가했던 것 같다. 다음 프로젝트에서는 보다 구체적이고 여유 있는 일정을 잡고, 예상치 못한 변수를 미리 고려하는 계획을 세울 것이다.

<h2>👍  좋았던점</h2>

- 이번 프로젝트에서 프론트엔드 개발을 주로 해오던 경험에 백엔드 기술을 접목시켜 전체적인 홈페이지를 완성하면서, 기술적으로 크게 성장했다고 느꼈고, 특히 백엔드 로직을 프론트엔드와 통합하여 데이터 흐름을 관리하고, 사용자 경험을 개선하는 과정에서 웹 애플리케이션의 전체적인 구조와 동작 방식을 더욱 깊이 이해하게 됐다.
- 아마존 웹 서비스를 활용하여 리눅스 서버 환경에서 배포하는 과정도 의미가 있었고, 처음에는 익숙하지 않았던 리눅스 커맨드를 사용하고, 파일 시스템과 서버 구조를 이해하면서 실질적으로 서버를 운영하는 경험을 쌓을 수 있었다. 이를 통해 배포 및 서버 관리에 대한 지식이 확장되었고, 추후 프로젝트에서도 이러한 기술들을 자신 있게 활용할 수 있을 것 같다.
- Spring 프레임워크의 전반적인 흐름을 이해하고, Controller, Service, Repository로 이어지는 구조가 개발에 주는 이점을 체감할 수 있었다. 단순한 기능 구현을 넘어서, 이러한 구조가 프로젝트 유지보수와 확장성에 얼마나 큰 영향을 미치는지 배웠다.

<h2>✈ 프로젝트 진행</h2>
(마우스 우클릭 통해 새창으로 열어 봐주시면 편하게 보실수 있습니다.)<br>
<h4>명품 기획서, DB, API, 일정 설계 - <a href="https://docs.google.com/spreadsheets/d/1ZCVBunnaFENWywUlvw_03PhH_Qrkz8RH05a36RrAKjk/edit?gid=1887178098#gid=1887178098">기획안</a></h4>

<h3># 메인</h3>

- 항상 프로젝트를 진행하게 되면 메인 컬러를 정하는데 이번 프로젝트 메인 컬러는 조금 늦게 결정됐다. <span style="color: #be68f5">보라계열</span> 로 진행됬다.
- 원래 초기 기획 홈페이지는 명품 입문자 가이드로 진행하려고 했지만 진행하다 보니 명품 입문자 보단 명품 소개 홈페이지가 된거같다.
- 메인은 위와 같은 swiper.js 를 사용해 배너 느낌으로 진행했다.
- 인기 브랜드 TOP6 는 고정되어있다. 외국의 조사결과에 의해 결정되어있다. <a href="https://brandirectory.com/rankings/luxury-and-premium/table">브랜드파이넨스 브랜드 디렉토리 럭셔리부문 랭킹</a>
- 금일의 추천 브랜드 TOP5 는 새로 고침하면 브랜드명 랜덤이미지5개가 나오도록 설정했다.
- HOT 중고 명품 마켓 인기 게시글 조회수에 높음에 따라 4개가 나오도록 설정했다.

<h3># Brand</h3>

- 브랜드는 여기어떄 홈페이지를 벤치마킹했다.
<img width="1275" alt="image" src="https://github.com/user-attachments/assets/8247a303-7d7d-461d-877b-aedc2b36c187">

- 나이대별, 브랜드별 그거에 맞는 가격과 이미지를 데이터로 넣었다.
- 전체리스트의 리스트, 필터링된 브랜드 리스트 가져오기, 나이대별, 브랜드별, 가격별로 많은 if문을 통해 데이터를 가져 왔다.
- 카카오맵 API를 가져와 브랜드를 선택하면 ex) 구찌, 루이비통 매장이 맵에 표시되게 설정했다.

```
<input type="radio" name="brand" value="Louis Vuitton" onclick="searchPlace(this.value)">
<p>루이비통(Louis Vuitton)</p>

function placesSearchCB(data, status, pagination) {
    // 검색 결과가 정상적으로 반환되었는지 상태를 확인하는 부분
    if (status === kakao.maps.services.Status.OK) {  
        // 지도에 표시할 범위를 저장하기 위한 LatLngBounds 객체 생성
        var bounds = new kakao.maps.LatLngBounds();  

        // 검색 결과로 받은 장소 데이터를 하나씩 반복 처리
        for (var i = 0; i < data.length; i++) {  
            // 각 장소에 마커 표시 함수 호출
            displayMarker(data[i]);    
            // LatLngBounds 객체에 현재 장소의 위치를 추가하여 지도 범위 설정
            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));  
        }       

        // 검색된 모든 장소의 위치를 포함하도록 지도의 범위 재설정
        map.setBounds(bounds); 
    } 
}

```

<h3> # HOT 중고 명품 마켓 인기 게시글 </h3>

- 중고 마켓 인기 게시글은 한페이지당 8개로 페이징처리를 진행했다.
- 섬네일 이미지 서머노트 사용으로 본인이 작성한 게시글만 수정 삭제 버튼이 나타나고 다른 사용자들한테는 보이지 않게 설정 했다.
- 게시글 세부사항으로 들어가게되면 댓글 작성이 가능하고, 댓글도 게시글과 똑같이 수정삭제 버튼이 나타나지만 다른 사용자들한테는 보이지 않게 설정했다.


<h3> # 로그인, 회원가입, 아이디, 비밀번호 찾기 </h3>

- 회원가입은 아이디중복체크를 하지않으면 회원가입 버튼이 안가게 설정되어있고
- 아이디 찾기, 비밀번호 찾기는 둘다 회원가입했던 이메일로 진행하게 되었고 , javamailsender, gmail을 사용했다.

<h3> # 검색 </h3>

- 키워드 검색 이후 홈페이지는 브랜드 이미지와 가격 상품이름만 나오게 설정하였고, 해당되는 키워드가 입력되지않으면 데이터가 없는 예외처리를 진행했고, 개사글도 똑같다.

<h3> # 배너등록 홈페이지(관리자전용) </h3>

- 배너등록 페이지는 이미지를 등록하고 제목을 적으면 swiper로 배너가 등록되는데
- 제목을 적으니 원하는 디자인은 텍스트가 항상 이미지의 중간으로 오기를 원했지만 계속 position: absolute; 이상한곳에 나와서 맘에들지 않았지만 해결했다
- css를 많이 아는것 같아도 항상 원하는대로 배치가 안되고 배울점이 항상 생기는것 같다. 


```
.swiper__title {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}
```

