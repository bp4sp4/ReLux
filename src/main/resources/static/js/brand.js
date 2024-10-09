// 가격 슬라이더 변화에따른 가격 추가
const slider = document.getElementById("priceRange");
const output = document.getElementById("priceValue");
output.textContent = slider.value + "만원";

slider.oninput = function () {
  output.textContent = this.value + "만원";
};

// 스크롤 이벤트
window.addEventListener("scroll", function () {
  const filter = document.querySelector(".main__filter");
  const scrollY = window.scrollY;

  if (scrollY > 200) {
    filter.classList.add("scrolled");
  } else {
    filter.classList.remove("scrolled");
  }
});

// 리셋버튼 활성화
$(document).ready(function () {
  const resetBtn = $("#resetBtn");

  function changeResetButtonColor() {
    resetBtn.css("color", "black");
  }

  $("input[type='radio']").on("change", function () {
    changeResetButtonColor();
  });

  $("#priceRange").on("input", function () {
    changeResetButtonColor();
  });

  resetBtn.on("click", function () {
    $("input[name='age'][value='전체']").prop("checked", true);
    $("input[name='brand'][value='전체']").prop("checked", true);
    $("#priceRange").val(0);
    $("#priceValue").text("0만원");
    resetBtn.css("color", "");
  });
});
