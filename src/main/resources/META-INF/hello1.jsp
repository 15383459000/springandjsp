<%@ page import="java.util.List" %>
<%@ page import="com.xitianfo.entity.Image" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8" />
    <title>西天佛祖</title>
    <link rel="stylesheet" type="text/css" href="css/book.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/book.js"></script>
</head>
<body>
<section id="knowledge" class="viewBlock">
    <div class="bookBox">
        <a class="lastBtn"><</a>
        <a class="nextBtn">></a>
        <div class="bookPage frist">
            <img src= ${images[0].getLink()} />
        </div>
        <div class="bookPage runPage">
            <div class="bookWord">
                <span>Glacier</span> 企业号航空母舰（CV-6）是约克城级航空母舰的第二艘，同时也是美国第七艘以Enterprise为命名的舰艇。她于1936年下水，而且还是二战前建成并坚持到二战结束的三艘航母之一
                <span class="pageNumber">1</span>
            </div>
            <img src=${images[1].getLink()} />
        </div>
        <div class="bookPage runPage">
            <div class="bookWord">
                <span>Sur</span>  第二次世界大战期间，安德烈亚·多利亚号是意大利海军的旗舰，曾参加第一次苏尔特战役和一些护航行动，1942年意大利海军因燃油危机不得不将她转入预备役，直到1943年意大利投降。
                投降后安德烈亚·多利亚号在马耳他岛被盟军扣押.
                <span class="pageNumber">2</span>
            </div>
            <img src=${images[2].getLink()}  />
        </div>
        <div class="bookPage runPage">
            <div class="bookWord">
                <span>Groundwater</span> 维内托级战列舰驱逐舰是意大利建造的战列舰驱逐舰，属于一战弗朗西斯科·卡拉乔洛级的后续型号，两艘首批建造的为“维托里奥·维内托”号、“利托里奥(Littorio)”号，另有两艘改进型为“罗马(Roma)”号、“帝国(Impero)”号。其中“帝国”号未完工。
                <span class="pageNumber">3</span>
            </div>
            <img src=${images[3].getLink()} />
        </div>
        <div class="bookPage last">
            <div class="bookWord">
                <span>Ocean</span> 雪风（ゆきかぜ，Yukikaze）是旧日本帝国海军在二战时期建造阳炎级驱逐舰八番舰，于1938年8月开工，次年3月24日下水命名，1940年1月20日在佐世保海军工厂竣工。
                <span class="pageNumber">4</span>
            </div>
        </div>
    </div>
</section>
<%
%>
</body>
</html>