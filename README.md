以 ViewPager 為參考，改寫成 垂直翻頁
VerticalViewPager 以及 VerticalPagerAdapter

再延伸出 Carousel
在 CarouselPager 裡有 setSwipeable 是在判斷是否可以翻頁
而 要做疊圖的處理請看 onLayout 那邊，去對子圖層做處理

