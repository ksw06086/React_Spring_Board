import React, { useEffect, useState } from 'react'
import Athentication from '../../Authentication'
import Navigation from '../../Navigation'
import { Box } from '@mui/material'
import BoardMain from '../../BoardMain'
import { useUserStore } from '../../../stores'
import { useCookies } from 'react-cookie'
import axios from 'axios'

export default function MainLayout() {

    const [boardResponse, setBoardResponse] = useState<string>('');
    const [cookies] = useCookies();
    const { user } = useUserStore();

    const getBoard = async (token: string) => {
        const requestOptions = {
            headers: {
                Authorization: `Bearer ${token}`,
            }
        }
        await axios.get('http://localhost:4000/api/board/', requestOptions)
            .then((res) => { setBoardResponse(res.data) })
            .catch((err) => null);
        }   

    useEffect(() => {
        const token = cookies.token;
        if(token) getBoard(token);
        else setBoardResponse('');
    }, [cookies.token]);

    return (
        <>
            <Navigation />
            { user ? (<BoardMain />) : (<Athentication />) }
        </>
    )
}
