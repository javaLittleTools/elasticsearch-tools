# elasticsearch-tools

## QueryBody

### index

Elasticsearch 索引

### page & size

分页

### match

match, multi match, match phrase 查询

#### field

字段名

#### fieldList

字段名列表

#### query

查询的值

#### slop

告诉 match_phrase 查询词条能够相隔多远时仍然将文档视为匹配

#### operator

操作符

##### and

每个词都需要匹配

##### or

满足任一匹配即可

#### miniShouldMatch

最小匹配度，默认为 **1**

### queryString

Query String 查询

#### defaultField

默认查询字段

#### fieldList

字段名列表

#### query

查询的值

### term

Term & Terms 查询

#### field

字段名

#### query

查询的值

#### queryList

查询的值列表

### range

Range 查询

#### field

字段名

#### gt

大于（数值）

#### gte

大于等于（数值）

#### lt

小于（数值）

#### lte

小于等于（数值）

#### from

从（日期）开始

#### to

到（日期）开始

#### boost

权重