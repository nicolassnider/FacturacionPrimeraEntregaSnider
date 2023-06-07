import React from 'react';
import styled from 'styled-components';

interface InvoiceDetails {
	title: string;
	total: string;
	createdAt: string;
}

interface CardProps {
	invoiceDetails: InvoiceDetails;
}

const Card = styled.div`
	background-color: black;
	color: white;
	padding: 16px;
	border-radius: 8px;
`;

const Title = styled.h2`
	font-size: 24px;
	margin-bottom: 8px;
`;

const Total = styled.p`
	font-size: 18px;
	margin-bottom: 8px;
`;

const CreatedAt = styled.p`
	font-size: 14px;
`;

const InvoiceCard: React.FC<CardProps> = ({ invoiceDetails: invoiceDetails }) => {
	return (
		<Card className="mt-2 ml-1 mr-5">
			<div className="flex flex-row">
				<div className="flex-1">
					<Title>{invoiceDetails.title}</Title>
					
				</div>
				<div className="flex-2">
					<button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
					onClick={}>
						open details
					</button>
				</div>
			</div>
			
		</Card>
	);
};

export default InvoiceCard;
