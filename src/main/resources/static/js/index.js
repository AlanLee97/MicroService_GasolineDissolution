function f() {
    $(function () {
        //1.轮播图
        $(window).on('resize',()=>{
            //1.获取窗口宽度
            let $clientW = $(window).width();
            //2.获取临界点
            let isShowBigImage = $clientW >= 900;
            //3.获取所有items
            let $allItems = $("#myLunbo .carousel-inner .carousel-item");
            // console.log(allItems);

            //4.遍历
            $allItems.each((index,item)=>{
                //console.log(item);
                // 4.1 取出图片路径
                let src = isShowBigImage ? $(item).data("lg-img") : $(item).data('sm-img');
                let imgUrl = `url("${src}")`;     //ES6语法
                //let smImgUrl = `${src}`;     //ES6语法
                //console.log(src);
                // 4.2 设置图片背景
                $(item).css({
                    backgroundImage: imgUrl
                });

                //4.3 创建img标签
                if (!isShowBigImage){ //小屏幕
                    let imgElem = `<img src="${src}" alt=""/>`;
                    $(item).empty().append(imgElem);

                } else { //大屏幕
                    $(item).empty();

                }
            });


        });
    });
}