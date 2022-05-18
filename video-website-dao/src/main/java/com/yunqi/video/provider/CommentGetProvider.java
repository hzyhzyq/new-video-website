package com.yunqi.video.provider;

public class CommentGetProvider {
    public String getProgress(final String videoId,String limit) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT comment_id,progress,content,user_id,user_name,video_id,in_box,create_time ");
        stringBuilder.append("FROM COMMENT ");
        stringBuilder.append("WHERE video_id = #{videoId} ");
        stringBuilder.append("ORDER BY rand() ");
        stringBuilder.append("LIMIT "+ limit);
        return stringBuilder.toString();
    }
    public String getTop(final String videoId){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("WITH"+"\n");
        stringBuilder.append("comment_in_box AS("+"\n");
        stringBuilder.append("SELECT comment_id,progress,content,user_id,user_name,video_id,in_box,create_time"+"\n");
        stringBuilder.append("FROM comment"+"\n");
        stringBuilder.append("WHERE video_id = "+videoId+" AND in_box = 1),"+"\n");
        stringBuilder.append("by_reply AS ("+"\n");
        stringBuilder.append("SELECT c.comment_id,count(1) AS count"+"\n");
        stringBuilder.append("FROM comment_in_box AS c"+"\n");
        stringBuilder.append("INNER JOIN reply AS r ON c.comment_id = r.comment_id"+"\n");
        stringBuilder.append("GROUP BY r.comment_id,c.comment_id"+"\n");
        stringBuilder.append("ORDER BY count DESC"+"\n");
        stringBuilder.append("LIMIT 10),"+"\n");
        stringBuilder.append("by_data AS ("+"\n");
        stringBuilder.append("SELECT c.comment_id,c.create_time"+"\n");
        stringBuilder.append("FROM comment_in_box AS c"+"\n");
        stringBuilder.append("LEFT JOIN by_reply AS b ON b.comment_id = c.comment_id"+"\n");
        stringBuilder.append("WHERE  b.comment_id IS NULL"+"\n");
        stringBuilder.append("ORDER BY c.create_time DESC"+"\n");
        stringBuilder.append("LIMIT 10)"+"\n");
        stringBuilder.append("SELECT c.comment_id,c.progress,c.content,c.user_id,c.user_name,c.video_id,c.in_box,c.create_time"+"\n");
        stringBuilder.append("FROM comment_in_box AS c"+"\n");
        stringBuilder.append("INNER JOIN ("+"\n");
        stringBuilder.append("SELECT comment_id FROM by_reply"+"\n");
        stringBuilder.append("UNION ALL"+"\n");
        stringBuilder.append("SELECT comment_id FROM by_data) AS a"+"\n");
        stringBuilder.append("ON c.comment_id = a.comment_id;"+"\n");
        return stringBuilder.toString();
    }
}
