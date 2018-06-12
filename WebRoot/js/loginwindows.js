    function g(id){return document.getElementById(id);}
    //  自动居中元素（el = Element）
    function autoCenter( el ){
        var bodyW = document.documentElement.clientWidth;
        var bodyH = document.documentElement.clientHeight;
        var elW = el.offsetWidth;
        var elH = el.offsetHeight;
        el.style.left = (bodyW-elW)/2 + 'px';
        el.style.top = (bodyH-elH)/2 + 'px';
    }
    //  自动扩展元素到全部显示区域
    function fillToBody( el ){
        el.style.width  = document.documentElement.clientWidth  +'px';
        el.style.height = document.documentElement.clientHeight + 'px';
    }
    //  重新调整对话框的位置和遮罩，并且展现
    function showDialog(){
        g('dialogMove').style.display = 'block';
        g('mask').style.display = 'block';
        autoCenter( g('dialogMove') );
        fillToBody( g('mask') );
    }

    //  关闭对话框
    function hideDialog(){
        g('dialogMove').style.display = 'none';
        g('mask').style.display = 'none';
    }

    function showDialog2(){
        g('dialogMove2').style.display = 'block';
        g('mask2').style.display = 'block';
        autoCenter( g('dialogMove2') );
        fillToBody( g('mask2') );
        hideDialog();
    }

    //  关闭对话框
    function hideDialog2(){
        g('dialogMove2').style.display = 'none';
        g('mask2').style.display = 'none';
    }

    function showDialog3(){
        g('dialogMove3').style.display = 'block';
        g('mask3').style.display = 'block';
        autoCenter( g('dialogMove3') );
        fillToBody( g('mask3') );
        hideDialog();
    }

    //  关闭对话框
    function hideDialog3(){
        g('dialogMove3').style.display = 'none';
        g('mask3').style.display = 'none';
    }
