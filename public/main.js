function getFiles(filesData){
    for (var i in filesData){
        var elem = $("<a>");
        elem.attr("href", "files/" + filesData.filename);
        elem.text((filesData[i].comment=="")?filesData[i].originalFilename:filesData[i].comment);
        $("#fileList").append(elem);
        var elem2 = $("<br>");
        $("#fileList").append(elem2);
    }
}
$.get("/files", getFiles);