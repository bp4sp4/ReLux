var swiper = new Swiper(".mySwiper", {
  slidesPerView: 1,
  centeredSlides: true,
  loop: true,
  autoplay: {
    delay: 4000,
    disableOnInteraction: true,
  },
  pagination: {
    el: ".swiper-pagination",
    clickable: true,
    type: "fraction",
  },
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
  breakpoints: {
    768: {
      slidesPerView: 2,
      spaceBetween: 40,
    },
    1024: {
      slidesPerView: 1.9,
      spaceBetween: 50,
    },
  },
});

// Hover 이벤트 유지
$(".mySwiper").each(function (elem, target) {
  var swp = target.swiper;
  $(this).hover(
    function () {
      swp.autoplay.stop();
    },
    function () {
      swp.autoplay.start();
    }
  );
});

var swiper2 = new Swiper(".mySwiper2", {
  slidesPerView: 2,
  centeredSlides: true,
  loop: true,
  autoplay: {
    delay: 4000,
    disableOnInteraction: true,
  },
  pagination: {
    el: ".swiper-pagination2",
    clickable: true,
    type: "fraction",
  },
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
  breakpoints: {
    768: {
      slidesPerView: 3,
      spaceBetween: 40,
    },
    1024: {
      slidesPerView: 2.5,
      spaceBetween: 30,
    },
  },
});
