function showPageList(span,totalPages,current) {
    if (current==0){
        createPageList(1,current+span,current)
    }
    else if (totalPages-current>1){          //如果总页数比当前页多一页以上，则往后多显示一页

        createPageList(current+3-span,current+2,current);        //显示页数是从1开始，故+1
    }
    else{           //当前页号与总页数相等，则往前多显示一页
        createPageList(current+2-span,current+1,current);
    }
    //修改尾页链接
    $("#end a")[0].href="/?pageNum="+totalPages;
}
function createPageList(begin,end,current) {
    for (var i = begin;i<=end;i++){
        var li = document.createElement("li");
        li.className="shadow-sm page-list";
        if (i==(current+1)) li.classList.add("page-list-active");
        var a = document.createElement("a");
        a.href="/?pageNum="+i;
        a.innerHTML=i;
        li.appendChild(a);
        $("#end").before(li);
    }

}