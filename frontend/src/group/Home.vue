<template>
    <div class="container">
        <b-navbar toggleable="lg" variant="light">
            <b-navbar-brand href="#">Woody Group</b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item><router-link to="/group/home/projects">Projects</router-link></b-nav-item>
                    <b-nav-item><router-link to="/group/home/members">Members</router-link></b-nav-item>
                </b-navbar-nav>

                <b-navbar-nav class="ml-auto">
                    <b-nav-item-dropdown right>
                        <template slot="button-content"><em>{{group.name}}</em></template>
                        <b-dropdown-item href="#">Settings</b-dropdown-item>
                        <b-dropdown-item href="#">Sign Out</b-dropdown-item>
                    </b-nav-item-dropdown>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
        <br>
        <router-view></router-view>

    </div>
</template>

<script>
    export default {
        data() {
            return {
                group: {}
            }
        },
        mounted() {
            this.loadGroupInfo()
            this.$router.push('/group/home/projects')
        },
        methods: {
            loadGroupInfo() {
                this.axios.get('/web/group/info').then(response => {
                    if (response && response.status == 200) {
                        this.group = response.data.result.group
                    }
                }).catch(() => {
                    this.$router.push('/sessionOut')
                })
            }
        }
    }
</script>
