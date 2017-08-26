const movie = {
    //保存变量
    variable: {},

    //URL存放
    url: {
        customers: () => {
            return "/customers"
        },
        session: () => {
            return "/session"
        },
        address: () => {
            return "/address"
        }
    },

    /*主页*/
    customer: {
        //初始化
        init: () => {

        },


        /**
         * 将数据渲染进模板并将生成的html插入DOM
         * @param temp 模板ID
         * @param data 数据对象
         * @param position 目的节点ID
         */
        render: (temp, data, position) => {

            //将ArtTemplate的界定符从%设置为？防止与jsp冲突
            let rule = template.defaults.rules[0]
            rule.test = new RegExp(rule.test.source.replace('<%', '<\\?').replace('%>', '\\?>'))

            //html 为数据渲染进模板后的内容
            const html = template(temp, data)

            //将渲染后的内容插入DOM
            document.getElementById(position).innerHTML = html
        },


        /**
         * 根据页码书获取JSON格式的Customer信息
         * @param pn 获取的页
         */
        getCustomerWithJson: (pn) => {
            $.ajax({
                url: movie.url.customers(),
                type: "get",
                dataType: "json",
                data: {
                    pn: pn
                },
                success: ({meta, data}) => {
                    //todo
                }
            })
        }
    },

    /*用户登录页*/
    session: {
        init: {
            //TODO
        }
    }
}


//TODO 记得删除
function c(str) {
    console.log(str)
}