    function g(id){return document.getElementById(id);}
    //  �Զ�����Ԫ�أ�el = Element��
    function autoCenter( el ){
        var bodyW = document.documentElement.clientWidth;
        var bodyH = document.documentElement.clientHeight;
        var elW = el.offsetWidth;
        var elH = el.offsetHeight;
        el.style.left = (bodyW-elW)/2 + 'px';
        el.style.top = (bodyH-elH)/2 + 'px';
    }
    //  �Զ���չԪ�ص�ȫ����ʾ����
    function fillToBody( el ){
        el.style.width  = document.documentElement.clientWidth  +'px';
        el.style.height = document.documentElement.clientHeight + 'px';
    }
    //  ���µ����Ի����λ�ú����֣�����չ��
    function showDialog(){
        g('dialogMove').style.display = 'block';
        g('mask').style.display = 'block';
        autoCenter( g('dialogMove') );
        fillToBody( g('mask') );
    }

    //  �رնԻ���
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

    //  �رնԻ���
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

    //  �رնԻ���
    function hideDialog3(){
        g('dialogMove3').style.display = 'none';
        g('mask3').style.display = 'none';
    }
